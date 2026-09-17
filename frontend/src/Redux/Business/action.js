import axios from "axios";
import {
  CREATE_BUSINESS_REQUEST,
  CREATE_BUSINESS_SUCCESS,
  CREATE_BUSINESS_FAILURE,
  UPDATE_BUSINESS_REQUEST,
  UPDATE_BUSINESS_SUCCESS,
  UPDATE_BUSINESS_FAILURE,
  FETCH_BUSINESSS_REQUEST,
  FETCH_BUSINESSS_SUCCESS,
  FETCH_BUSINESSS_FAILURE,
  FETCH_BUSINESS_BY_ID_REQUEST,
  FETCH_BUSINESS_BY_ID_SUCCESS,
  FETCH_BUSINESS_BY_ID_FAILURE,
  FETCH_BUSINESS_BY_OWNER_REQUEST,
  FETCH_BUSINESS_BY_OWNER_SUCCESS,
  FETCH_BUSINESS_BY_OWNER_FAILURE,
  SEARCH_BUSINESSS_REQUEST,
  SEARCH_BUSINESSS_SUCCESS,
  SEARCH_BUSINESSS_FAILURE,
} from "./actionTypes";
import api from "../../config/api";

const API_BASE_URL = "/api/businesses";

export const createBusiness = (reqData) => async (dispatch) => {
  dispatch({ type: CREATE_BUSINESS_REQUEST });
  try {
    const response = await api.post(`/auth/signup`, reqData.ownerDetails);

    console.log("response ", response.data);

    localStorage.setItem("jwt", response.data.data.jwt);

    const { data } = await api.post(API_BASE_URL, reqData.businessDetails, {
      headers: { Authorization: `Bearer ${response.data.data.jwt}` },
    });

    reqData.navigate("/business-dashboard");

    console.log("business created successfully", data);
    dispatch({ type: CREATE_BUSINESS_SUCCESS, payload: data });
  } catch (error) {
    console.log("error creating business", error);
    dispatch({ type: CREATE_BUSINESS_FAILURE, payload: error.message });
  }
};

export const updateBusiness = (businessId, business) => async (dispatch) => {
  dispatch({ type: UPDATE_BUSINESS_REQUEST });
  try {
    const { data } = await api.put(`${API_BASE_URL}/${businessId}`, business,{
      headers:{
        Authorization: `Bearer ${localStorage.getItem("jwt")}`,
      }
    });
    dispatch({ type: UPDATE_BUSINESS_SUCCESS, payload: data });
  } catch (error) {
    dispatch({ type: UPDATE_BUSINESS_FAILURE, payload: error.message });
  }
};

export const fetchBusinesses = () => async (dispatch) => {
  dispatch({ type: FETCH_BUSINESSS_REQUEST });
  try {
    const { data } = await api.get(API_BASE_URL,{
      headers:{
        Authorization: `Bearer ${localStorage.getItem("jwt")}`,
      }
    });
    console.log("all businesses ",data)
    dispatch({ type: FETCH_BUSINESSS_SUCCESS, payload: data });
  } catch (error) {
    console.log("error fetching businesses", error);
    dispatch({ type: FETCH_BUSINESSS_FAILURE, payload: error.message });
  }
};

export const fetchBusinessById = (businessId) => async (dispatch) => {
  dispatch({ type: FETCH_BUSINESS_BY_ID_REQUEST });
  try {
    const { data } = await api.get(`${API_BASE_URL}/${businessId}`,{
      headers:{
        Authorization: `Bearer ${localStorage.getItem("jwt")}`,
      }
    });
    dispatch({ type: FETCH_BUSINESS_BY_ID_SUCCESS, payload: data });
  } catch (error) {
    dispatch({ type: FETCH_BUSINESS_BY_ID_FAILURE, payload: error.message });
  }
};

export const fetchBusinessByOwner = (jwt) => async (dispatch) => {
  dispatch({ type: FETCH_BUSINESS_BY_OWNER_REQUEST });
  try {
    const { data } = await api.get(`${API_BASE_URL}/owner`, {
      headers: { Authorization: `Bearer ${jwt}` },
    });
    console.log("business by owner - ", data);
    dispatch({ type: FETCH_BUSINESS_BY_OWNER_SUCCESS, payload: data });
  } catch (error) {
    console.log("error fetching business by owner - ", error);
    dispatch({ type: FETCH_BUSINESS_BY_OWNER_FAILURE, payload: error.message });
  }
};

export const searchBusiness = ({jwt,city}) => async (dispatch) => {
  dispatch({ type: SEARCH_BUSINESSS_REQUEST });
  try {
    const { data } = await api.get(`${API_BASE_URL}/search`, {
      headers: { Authorization: `Bearer ${jwt}` },
      params: { city: city },
    });
    console.log("Search business - ", data);
    dispatch({ type: SEARCH_BUSINESSS_SUCCESS, payload: data });
  } catch (error) {
    console.log("error fetching business by owner - ", error);
    dispatch({ type: SEARCH_BUSINESSS_FAILURE, payload: error.message });
  }
};
