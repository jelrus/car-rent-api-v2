import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axiosInstance from '@/utils/axiosInstance';

export const getBookings = createAsyncThunk('bookings/fetchBookings', async (clientId, thunkAPI) => {
  try {
    const response = await axiosInstance.get(`/bookings/${clientId}`);
    return response.data.content;
  } catch (error) {
    return thunkAPI.rejectWithValue(error.response.data);
  }
});

const bookingSlice = createSlice({
  name: 'bookings',
  initialState: {
    bookings: [],
    loading: false,
    error: null,
  },
  extraReducers: (builder) => {
    builder
      .addCase(getBookings.pending, (state) => {
        state.loading = true;
      })
      .addCase(getBookings.fulfilled, (state, action) => {
        state.bookings = action.payload;
        state.loading = false;
      })
      .addCase(getBookings.rejected, (state, action) => {
        state.error = action.payload?.message || 'Something went wrong';
        state.loading = false;
      });
  },
});

export default bookingSlice.reducer;