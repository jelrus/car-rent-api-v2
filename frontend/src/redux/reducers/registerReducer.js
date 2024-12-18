import {
  REGISTER_FAILURE,
  REGISTER_SUCCESS,
  REGISTER_REQUEST,
} from '../constants/registerContants';

const initialState = {
  user: null,
  error: null,
  loading: false,
  isAuth: false,
  role: null,
};
export const registrationReducer = (state = initialState, action) => {
  switch (action.type) {
    case REGISTER_REQUEST:
      return {
        ...state,
        loading: true,
        error: null,
      };
    case REGISTER_SUCCESS:
      return {
        ...state,
        user: action.payload,
        isAuth: true,
        loading: false,
        role: action.payload.role,
        error: null,
      };
    case REGISTER_FAILURE:
      return {
        ...state,
        error: action.payload,
        loading: false,
        isAuth: false,
      };
    default:
      return state;
  }
};
export default registrationReducer;
