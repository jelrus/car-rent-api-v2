import { combineReducers } from "@reduxjs/toolkit"
import registrationReducer from "./reducers/registerReducer"
import authReducer from './features/authSlice';
const rootReducer=combineReducers({
	register:registrationReducer,
	auth: authReducer,
})
export default rootReducer