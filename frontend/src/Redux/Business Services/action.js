import axios from "axios";
import {
  CREATE_SERVICE_REQUEST,
  CREATE_SERVICE_SUCCESS,
  CREATE_SERVICE_FAILURE,
  UPDATE_SERVICE_REQUEST,
  UPDATE_SERVICE_SUCCESS,
  UPDATE_SERVICE_FAILURE,
  FETCH_SERVICES_BY_BUSINESS_REQUEST,
  FETCH_SERVICES_BY_BUSINESS_SUCCESS,
  FETCH_SERVICES_BY_BUSINESS_FAILURE,
  FETCH_SERVICE_BY_ID_REQUEST,
  FETCH_SERVICE_BY_ID_SUCCESS,
  FETCH_SERVICE_BY_ID_FAILURE,
} from "./actionTypes";
import api from "../../config/api";

const API_BASE_URL = "/api/service-offering";

export const createServiceAction =
  ({ service, jwt }) =>
  async (dispatch) => {
    dispatch({ type: CREATE_SERVICE_REQUEST });

    try {
      const { data } = await api.post(`${API_BASE_URL}/business-owner`, service, {
        headers: { Authorization: `Bearer ${jwt}` },
      });
      console.log("service created", data);
      dispatch({ type: CREATE_SERVICE_SUCCESS, payload: data });
    } catch (error) {
      console.log("error creating service", error);
      dispatch({ type: CREATE_SERVICE_FAILURE, payload: error.message });
    }
  };

export const updateService = ({id, service}) => async (dispatch) => {
  dispatch({ type: UPDATE_SERVICE_REQUEST });
  try {
    const { data } = await api.patch(`${API_BASE_URL}/business-owner/${id}`, service,{
      headers: { Authorization: `Bearer ${localStorage.getItem("jwt")}` },
    });
    dispatch({ type: UPDATE_SERVICE_SUCCESS, payload: data });
    console.log("service updated", data);
  } catch (error) {
    console.log("error updating service", error);
    dispatch({ type: UPDATE_SERVICE_FAILURE, payload: error });
  }
};

export const fetchServicesByBusinessId =
  ({ businessId, jwt, categoryId }) =>
  async (dispatch) => {
    dispatch({ type: FETCH_SERVICES_BY_BUSINESS_REQUEST });
    try {
      const { data } = await api.get(`${API_BASE_URL}/business/${businessId}`, {
        headers: { Authorization: `Bearer ${jwt}` },
        params: { categoryId },
      });
      console.log("all services ", data);
      dispatch({ type: FETCH_SERVICES_BY_BUSINESS_SUCCESS, payload: data });
    } catch (error) {
      console.log("fetch all services ", error);
      dispatch({
        type: FETCH_SERVICES_BY_BUSINESS_FAILURE,
        payload: error.message,
      });
    }
  };

export const fetchServiceById = (serviceId) => async (dispatch) => {
  dispatch({ type: FETCH_SERVICE_BY_ID_REQUEST });
  try {
    const { data } = await api.get(`${API_BASE_URL}/${serviceId}`, {
      headers: { Authorization: `Bearer ${localStorage.getItem("jwt")}` },
    });
    dispatch({ type: FETCH_SERVICE_BY_ID_SUCCESS, payload: data });
    console.log("service by id ", data);
  } catch (error) {
    dispatch({
      type: FETCH_SERVICE_BY_ID_FAILURE,
      payload: error,
    });
  }
};
