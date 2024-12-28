import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const fetchCars = createAsyncThunk(
  'cars/fetchCars',
  async (_, { rejectWithValue }) => {
    try {
      const response = await axios.get('/cars.json');
      return response.data;
    } catch (error) {
      return rejectWithValue(error.response?.data?.message || error.message);
    }
  },
);

const carsSlice = createSlice({
  name: 'cars',
  initialState: {
    carsData: [],
    filteredCarsData: [],
    pickupLocations: [],
    dropOffLocations: [],
    categories: [],
    gearBoxies: [],
    fuelTypes: [],
    minPrice: 0,
    maxPrice: 0,
    loading: false,
    error: null,
  },
  reducers: {
    applyFilters: (state, action) => {
      const filters = action.payload;

      const filtered = state.carsData.filter((car) => {
        const isPickupMatch = filters.pickupLocationId
          ? car.pickupLocationId === filters.pickupLocationId
          : true;
        const isDropOffMatch = filters.dropOffLocationId
          ? car.dropOffLocationId === filters.dropOffLocationId
          : true;
        const isCategoryMatch = filters.category
          ? car.category === filters.category
          : true;
        const isGearBoxMatch = filters.gearBoxType
          ? car.gearBoxType === filters.gearBoxType
          : true;
        const isFuelTypeMatch = filters.fuelType
          ? car.fuelType === filters.fuelType
          : true;
        const isPriceMatch =
          car.pricePerDay >= filters.priceRange[0] &&
          car.pricePerDay <= filters.priceRange[1];

        return (
          isPickupMatch &&
          isDropOffMatch &&
          isCategoryMatch &&
          isGearBoxMatch &&
          isFuelTypeMatch &&
          isPriceMatch
        );
      });

      state.filteredCarsData = filtered;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchCars.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(fetchCars.fulfilled, (state, action) => {
        const data = action.payload;

        if (!data || data.length === 0) {
          state.loading = false;
          state.error = 'No cars data available';
          state.carsData = [];
          state.filteredCarsData = [];
          return;
        }

        const availableCars = data.filter((car) => car.status === 'AVAILABLE');

        if (availableCars.length === 0) {
          state.loading = false;
          state.error = 'No available cars found';
          state.carsData = [];
          state.filteredCarsData = [];
          return;
        }

        const prices = availableCars.map((car) => car.pricePerDay);
        state.minPrice = Math.min(...prices);
        state.maxPrice = Math.max(...prices);

        state.loading = false;
        state.carsData = availableCars;
        state.filteredCarsData = availableCars;

        state.pickupLocations = [
          ...new Set(availableCars.map((car) => car.pickupLocationId)),
        ];
        state.dropOffLocations = [
          ...new Set(availableCars.map((car) => car.dropOffLocationId)),
        ];
        state.categories = [
          ...new Set(availableCars.map((car) => car.category)),
        ];
        state.gearBoxies = [
          ...new Set(availableCars.map((car) => car.gearBoxType)),
        ];
        state.fuelTypes = [
          ...new Set(availableCars.map((car) => car.fuelType)),
        ];
      })
      .addCase(fetchCars.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      });
  },
});

export const { applyFilters } = carsSlice.actions;
export default carsSlice.reducer;
