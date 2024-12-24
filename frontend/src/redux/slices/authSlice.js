import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axiosInstance from '@/utils/axiosInstance';

export const loginUser = createAsyncThunk('auth/login', async (data) => {
  const response = await axiosInstance.post('users/login', data);

  const { accessToken, userId, role, userImageUrl, username } = response.data;
  
  sessionStorage.setItem('token', accessToken);
  sessionStorage.setItem('userId', userId);
  sessionStorage.setItem('role', role);
  sessionStorage.setItem('userImageUrl,', userImageUrl,);
  sessionStorage.setItem('username', username);
  sessionStorage.setItem('email', data.email);

  return response.data; 
});


const authSlice = createSlice({
  name: 'auth',
  initialState: {
    token: sessionStorage.getItem('token') || null,
    user: {
      role: sessionStorage.getItem('role') || null,
      userId: sessionStorage.getItem('userId') || null,
      userImageUrl: sessionStorage.getItem('userImageUrl') || null,
      username: sessionStorage.getItem('username') || null,
      email: sessionStorage.getItem('email') || null,
    },
    loading: false,
    authError: null,
  },
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
        state.user.role = action.payload.role;
        state.user.userId = action.payload.userId;
        state.user.userImageUrl = action.payload.userImageUrl;
        state.user.username = action.payload.username;
      })
      .addCase(loginUser.rejected, (state, action) => {
        state.loading = false;
        state.authError = action.error.message;
      });
  },
});

export const { logOut, setAuthError } = authSlice.actions;
export default authSlice.reducer;
