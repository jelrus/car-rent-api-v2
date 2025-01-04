import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axiosInstance from '@/utils/axiosInstance';
import axios from 'axios';

export const fetchCars = createAsyncThunk(
  'cars/fetchCars',
  async (filters, thunkAPI) => {
    try {
      const response = await axiosInstance.get('/cars', {
        params: {
          ...filters,
        },
      });
      return response.data;
    } catch (error) {
      return thunkAPI.rejectWithValue(error.response?.data || error.message);
    }
  },
);

// export const fetchBookedDays = createAsyncThunk(
//   'cars/fetchBookedDays',
//   async (carId, thunkAPI) => {
//     try {
//       const response = await axiosInstance.get(`/cars/${carId}/booked-days`);
//       return { carId, bookedDays: response.data.content };
//     } catch (error) {
//       return thunkAPI.rejectWithValue(error.response?.data || error.message);
//     }
//   },
// );

export const fetchBookedDays = createAsyncThunk(
  'cars/fetchBookedDays',
  async (carId, thunkAPI) => {
    try {
      const response = await axios.get('/cars.json');
      const car = response.data.find((car) => car.carId === carId);
      if (!car) throw new Error('Car not found');
      return { carId, bookedDays: car.bookedDays || [] };
    } catch (error) {
      return thunkAPI.rejectWithValue(error.message);
    }
  },
);

const carsSlice = createSlice({
  name: 'cars',
  initialState: {
    carsData: [],
    totalPages: 1,
    totalElements: 0,
    currentPage: 1,
    filters: {},
    loading: false,
    error: null,
  },
  reducers: {
    setFilters: (state, action) => {
      state.filters = action.payload;
    },
    clearFilters: (state) => {
      state.filters = {};
    },
    setCurrentPage: (state, action) => {
      state.currentPage = action.payload;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchCars.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(fetchCars.fulfilled, (state, action) => {
        state.loading = false;
        const { content, currentPage, totalPages, totalElements } =
          action.payload;

        state.carsData = content;
        state.currentPage = currentPage;
        state.totalPages = totalPages;
        state.totalElements = totalElements;
      })
      .addCase(fetchCars.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      });
  },
});

export const { setFilters, clearFilters, setCurrentPage } = carsSlice.actions;
export default carsSlice.reducer;
