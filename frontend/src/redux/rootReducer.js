import { combineReducers } from '@reduxjs/toolkit';
import registrationReducer from './reducers/registerReducer';
import authReducer from './slices/authSlice';
import bookingsReducer from './slices/bookingsSlice'
const rootReducer = combineReducers({
  register: registrationReducer,
  auth: authReducer,
  bookings: bookingsReducer,
});
export default rootReducer;
