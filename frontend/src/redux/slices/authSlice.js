import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axiosInstance from '@/utils/axiosInstance';

export const loginUser = createAsyncThunk('auth/login', async (data) => {
  const response = await axiosInstance.post('users/login', data);
  return response.data; 
});

const authSlice = createSlice({
  name: 'auth',
  initialState: {
    token: null,
    role: null,
    userId: null,
    userImageUrl: null,
    username: null,
    loading: false,
    authError: null,
  },
  reducers: {
    logOut: (state) => {
      state.token = null;
      state.role = null;
      state.userId = null;
      state.userImageUrl = null;
      state.username = null;
    },
    setAuthError: (state, action) => {
      state.authError = action.payload;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(loginUser.pending, (state) => {
        state.loading = true;
      })
      .addCase(loginUser.fulfilled, (state, action) => {
        state.loading = false;
        state.token = action.payload.accessToken;
        state.role = action.payload.role;
        state.userId = action.payload.userId;
        state.userImageUrl = action.payload.userImageUrl;
        state.username = action.payload.username;
      })
      .addCase(loginUser.rejected, (state, action) => {
        state.loading = false;
        state.authError = action.error.message;
      });
  },
});

export const { logOut, setAuthError } = authSlice.actions;
export default authSlice.reducer;