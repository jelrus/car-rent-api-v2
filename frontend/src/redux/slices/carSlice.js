import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axiosInstance from '@/utils/axiosInstance';

export const getCarDetails = createAsyncThunk(
  'cars/getCarDetails',
  async (carId, thunkAPI) => {
    try {
      const response = await axiosInstance.get(`/cars/${carId}`);
      return response.data;
    } catch (error) {
      return thunkAPI.rejectWithValue(error.response.data);
    }
  },
);

export const getBookedDays = createAsyncThunk(
  'cars/getBookedDays',
  async (carId, thunkAPI) => {
    try {
      const response = await axiosInstance.get(`/cars/${carId}/booked-days`);
      return response.data.content;
    } catch (error) {
      return thunkAPI.rejectWithValue(error.response.data);
    }
  },
);

const carSlice = createSlice({
  name: 'cars',
  initialState: {
    carDetails: null,
    bookedDays: [],
    loading: false,
    error: null,
  },
  reducers: {},
  extraReducers: (builder) => {
    builder

      .addCase(getCarDetails.pending, (state) => {
        state.loading = true;
      })
      .addCase(getCarDetails.fulfilled, (state, action) => {
        state.carDetails = action.payload;
        state.loading = false;
      })
      .addCase(getCarDetails.rejected, (state, action) => {
        state.error = action.payload?.message || 'Failed to fetch car details';
        state.loading = false;
      })

      .addCase(getBookedDays.pending, (state) => {
        state.loading = true;
      })
      .addCase(getBookedDays.fulfilled, (state, action) => {
        state.bookedDays = action.payload;
        state.loading = false;
      })
      .addCase(getBookedDays.rejected, (state, action) => {
        state.error = action.payload?.message || 'Failed to fetch booked days';
        state.loading = false;
      });
  },
});

export default carSlice.reducer;
