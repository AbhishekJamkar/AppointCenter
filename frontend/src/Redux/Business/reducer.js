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
  SEARCH_BUSINESSS_SUCCESS,
  SEARCH_BUSINESSS_REQUEST,
  SEARCH_BUSINESSS_FAILURE,
} from "./actionTypes";

const initialState = {
  businesses: [],
  business: null,
  searchBusinesses: [],
  loading: false,
  error: null,
};

const businessReducer = (state = initialState, action) => {
  switch (action.type) {
    case CREATE_BUSINESS_REQUEST:
    case UPDATE_BUSINESS_REQUEST:
    case FETCH_BUSINESSS_REQUEST:
    case FETCH_BUSINESS_BY_ID_REQUEST:
    case FETCH_BUSINESS_BY_OWNER_REQUEST:
    case SEARCH_BUSINESSS_REQUEST:
      return { ...state, loading: true, error: null };

    case CREATE_BUSINESS_SUCCESS:
      return { ...state, loading: false, business: action.payload };

    case UPDATE_BUSINESS_SUCCESS:
      return { ...state, loading: false, business: action.payload };

    case SEARCH_BUSINESSS_SUCCESS:
      return { ...state, loading: false, searchBusinesses: action.payload };

    case FETCH_BUSINESSS_SUCCESS:
      return { ...state, loading: false, businesses: action.payload };

    case FETCH_BUSINESS_BY_ID_SUCCESS:
    case FETCH_BUSINESS_BY_OWNER_SUCCESS:
      return { ...state, loading: false, business: action.payload };

    case CREATE_BUSINESS_FAILURE:
    case UPDATE_BUSINESS_FAILURE:
    case FETCH_BUSINESSS_FAILURE:
    case FETCH_BUSINESS_BY_ID_FAILURE:
    case FETCH_BUSINESS_BY_OWNER_FAILURE:
    case SEARCH_BUSINESSS_FAILURE:
      return { ...state, loading: false, error: action.payload };

    default:
      return state;
  }
};

export default businessReducer;
