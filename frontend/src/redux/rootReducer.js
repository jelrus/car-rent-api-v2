import { combineReducers } from '@reduxjs/toolkit';
import registrationReducer from './reducers/registerReducer';
import authReducer from './slices/authSlice';
import carsReducer from './slices/carsSlice';

const rootReducer = combineReducers({
  register: registrationReducer,
  auth: authReducer,
  cars: carsReducer,
});
export default rootReducer;
