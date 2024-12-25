import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';
import axios from 'axios';

const API_URL = import.meta.env.VITE_SERVER_API;

export const registerUser = createAsyncThunk(
  'auth/registerUser',
  async (userData, { rejectWithValue }) => {
    try {
      const response = await axios.post(`${API_URL}/signup`, userData);
      const { accessToken, userId, role, userImageUrl, username, email } = response.data;

      sessionStorage.setItem('token', accessToken);
      sessionStorage.setItem('userId', userId);
      sessionStorage.setItem('role', role);
      sessionStorage.setItem('userImageUrl', userImageUrl);
      sessionStorage.setItem('username', username);
      sessionStorage.setItem('email', email);

      return response.data;
    } catch (error) {
      return rejectWithValue(error.response?.data?.message || error.message);
    }
  }
);

const initialState = {
  token: sessionStorage.getItem('token') || null,
  user: {
    role: sessionStorage.getItem('role') || null,
    userId: sessionStorage.getItem('userId') || null,
    userImageUrl: sessionStorage.getItem('userImageUrl') || null,
    username: sessionStorage.getItem('username') || null,
    email: sessionStorage.getItem('email') || null,
  },
  error: null,
  loading: false,
};

const registrationSlice = createSlice({
  name: 'auth',
  initialState,
  reducers: {
    logOut: (state) => {
      state.token = null;
      state.user.role = null;
      state.user.userId = null;
      state.user.userImageUrl = null;
      state.user.username = null;
      state.user.email = null;

      sessionStorage.removeItem('token');
      sessionStorage.removeItem('userId');
      sessionStorage.removeItem('role');
      sessionStorage.removeItem('userImageUrl');
      sessionStorage.removeItem('username');
      sessionStorage.removeItem('email');
    },
    setAuthError: (state, action) => {
      state.error = action.payload;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(registerUser.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(registerUser.fulfilled, (state, action) => {
        state.loading = false;
        state.token = action.payload.accessToken;
        state.user.role = action.payload.role;
        state.user.userId = action.payload.userId;
        state.user.userImageUrl = action.payload.userImageUrl;
        state.user.username = action.payload.username;
        state.user.email = action.payload.email;
        state.error = null;
      })
      .addCase(registerUser.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      });
  },
});

export const { logOut, setAuthError } = registrationSlice.actions;
export default registrationSlice.reducer;
