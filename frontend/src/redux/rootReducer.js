import { combineReducers } from '@reduxjs/toolkit';
import authReducer from './slices/authSlice';
import carsReducer from './slices/carsSlice';
import bookingsReducer from './slices/bookingsSlice'

const rootReducer = combineReducers({
  auth: authReducer,
  cars: carsReducer,
  bookings: bookingsReducer,
});
export default rootReducer;
