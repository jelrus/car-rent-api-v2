import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axiosInstance from '@/utils/axiosInstance';

export const loginUser = createAsyncThunk('auth/login', async (data, { rejectWithValue }) => {
  try {
    const response = await axiosInstance.post('users/login', data);
    const { accessToken, userId, role, userImageUrl, username } = response.data;
    
    sessionStorage.setItem('token', accessToken);
    sessionStorage.setItem('userId', userId);
    sessionStorage.setItem('role', role);
    sessionStorage.setItem('userImageUrl', userImageUrl);
    sessionStorage.setItem('username', username);
    sessionStorage.setItem('email', data.email);

    return response.data;
  } catch (error) {
    return rejectWithValue(error.response?.data?.message || error.message);
  }
});

export const registerUser = createAsyncThunk(
  'auth/registerUser',
  async (userData, { rejectWithValue }) => {
    try {
      const response = await axiosInstance.post(`users`, userData);
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
  loading: false,
  error: null,
};

const authSlice = createSlice({
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
      .addCase(loginUser.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(loginUser.fulfilled, (state, action) => {
        state.loading = false;
        const { accessToken, userId, role, userImageUrl, username, email } = action.payload;
        state.token = accessToken;
        state.user = { userId, role, userImageUrl, username, email };
        state.error = null;
      })
      .addCase(loginUser.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      });

    builder
      .addCase(registerUser.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(registerUser.fulfilled, (state, action) => {
        state.loading = false;
        const { accessToken, userId, role, userImageUrl, username, email } = action.payload;
        state.token = accessToken;
        state.user = { userId, role, userImageUrl, username, email };
        state.error = null;
      })
      .addCase(registerUser.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      });
  },
});

export const { logOut, setAuthError } = authSlice.actions;
export default authSlice.reducer;
