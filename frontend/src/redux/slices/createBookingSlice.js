import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axiosInstance from '@/utils/axiosInstance';

export const createBooking = createAsyncThunk(
  'bookings/createBooking',
  async (bookingData, thunkAPI) => {
    try {
      const response = await axiosInstance.post('/bookings', bookingData);
      return response.data;
    } catch (error) {
      return thunkAPI.rejectWithValue(error.response.data);
    }
  },
);

const initialState = {
  data: null,
  loading: false,
  error: null,
};

const createBookingSlice = createSlice({
  name: 'createBooking',
  initialState,
  extraReducers: (builder) => {
    builder
      .addCase(createBooking.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(createBooking.fulfilled, (state, action) => {
        state.data = action.payload;
        state.loading = false;
      })
      .addCase(createBooking.rejected, (state, action) => {
        state.error = action.payload?.message || 'Something went wrong';
        state.loading = false;
      });
  },
});

export default createBookingSlice.reducer;
