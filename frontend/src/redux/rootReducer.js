import { combineReducers } from '@reduxjs/toolkit';
import authReducer from './slices/authSlice';
import carsReducer from './slices/carsSlice';
import bookingsReducer from './slices/bookingsSlice'
import createBookingReducer from './slices/createBookingSlice'
import bookedCarReducer from './slices/carSlice'

const rootReducer = combineReducers({
  auth: authReducer,
  cars: carsReducer,
  bookings: bookingsReducer,
  createBooking:createBookingReducer,
  carBooked: bookedCarReducer
});
export default rootReducer;
