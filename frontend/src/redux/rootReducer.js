import { combineReducers } from '@reduxjs/toolkit';
import registrationReducer from './reducers/registerReducer';
import authReducer from './slices/authSlice';
import carsReducer from './slices/carsSlice';
import bookingsReducer from './slices/bookingsSlice'

const rootReducer = combineReducers({
  register: registrationReducer,
  auth: authReducer,
  cars: carsReducer,
  bookings: bookingsReducer,
});
export default rootReducer;
