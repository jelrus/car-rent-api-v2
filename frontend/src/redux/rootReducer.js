import { combineReducers } from "@reduxjs/toolkit"
import registrationReducer from "./reducers/registerReducer"

const rootReducer=combineReducers({
	register:registrationReducer
})
export default rootReducer