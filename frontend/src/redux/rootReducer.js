import { combineReducers } from '@reduxjs/toolkit';
import registrationReducer from './reducers/registerReducer';
import authReducer from './slices/authSlice';
const rootReducer = combineReducers({
  register: registrationReducer,
  auth: authReducer,
});
export default rootReducer;
