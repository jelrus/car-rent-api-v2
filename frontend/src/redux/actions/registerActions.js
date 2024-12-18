import { REGISTER_FAILURE,REGISTER_SUCCESS, REGISTER_REQUEST } from "../constants/registerContants";
import axios from "axios"
const API_URL=import.meta.env.VITE_SERVER_API
export const registerUser=(userData)=>async(dispatch)=>{
	dispatch({type:REGISTER_REQUEST});
	try{
		dispatch({ type: REGISTER_REQUEST });
		try {
			const response = await axios.post(`${API_URL}/signup`, userData);
			dispatch({ type: REGISTER_SUCCESS, payload: response.data });
		} catch (error) {
			dispatch({ type: REGISTER_FAILURE, payload: error.message });
		}
	}catch (error) {
    dispatch({ type: REGISTER_FAILURE, error: error.message });
  }
}