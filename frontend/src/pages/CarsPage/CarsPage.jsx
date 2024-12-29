import { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { fetchCars, setFilters } from '@/redux/slices/carsSlice';
import FiltersSection from '@pages/CarsPage/components/FiltersSection/FiltersSection.jsx';
import GeneralCarCard from '@pages/CarsPage/components/CarCard/GeneralCarCard.jsx';
import Pagination from './components/Pagination/Pagination.jsx';
import CarDetailsModal from './components/CarDetailsModal/CarDetailsModal.jsx';
import { useSearchParams } from 'react-router-dom';

import './CarsPage.css';

const CarsPage = () => {
  const [searchParams, setSearchParams] = useSearchParams();
  const dispatch = useDispatch();
  const {
    filteredCarsData = [],
    pickupLocations = [],
    dropOffLocations = [],
    categories = [],
    gearBoxies = [],
    fuelTypes = [],
    minPrice = 0,
    maxPrice = 0,
    loading = false,
    error = null,
    // filters,
  } = useSelector((state) => state.cars);

  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 16;
  const [isTransitioning, setIsTransitioning] = useState(false);
  const [selectedCar, setSelectedCar] = useState(null);
  // const [isModalOpen, setIsModalOpen] = useState(false);

  useEffect(() => {
    const params = Object.fromEntries([...searchParams]);
    const parsedFilters = {
      ...params,
      priceRange: params.priceRange
        ? params.priceRange.split(',').map(Number)
        : [minPrice, maxPrice],
    };

    dispatch(fetchCars(parsedFilters));
    dispatch(setFilters(parsedFilters));
  }, [dispatch, searchParams, minPrice, maxPrice]);

  useEffect(() => {
    const params = Object.fromEntries([...searchParams]);

    if (!filteredCarsData || filteredCarsData.length === 0) {
      setSelectedCar(null);
      return;
    }

    if (params.carId) {
      const selected = filteredCarsData.find(
        (car) => car.carId === params.carId,
      );
      setSelectedCar(selected || null);
    }
  }, [searchParams, filteredCarsData]);

  const handleApplyFilters = (newFilters) => {
    console.log('Filters applied:', newFilters);
    dispatch(setFilters(newFilters));
    dispatch(fetchCars(newFilters));
    setCurrentPage(1);
  };

  const indexOfLastItem = currentPage * itemsPerPage;
  const indexOfFirstItem = indexOfLastItem - itemsPerPage;
  const currentItems = filteredCarsData.slice(
    indexOfFirstItem,
    indexOfLastItem,
  );
  const totalPages = Math.ceil(filteredCarsData.length / itemsPerPage);

  const handlePageChange = (page) => {
    if (page !== currentPage) {
      setIsTransitioning(true);
      setTimeout(() => {
        setCurrentPage(page);
        setIsTransitioning(false);
      }, 300);
    }
  };

  const handleOpenModal = (car) => {
    setSelectedCar(car);
    setSearchParams((prev) => ({
      ...Object.fromEntries([...prev]),
      carId: car.carId,
    }));
  };

  const handleCloseModal = () => {
    setSelectedCar(null);
    setSearchParams((prev) => {
      const params = Object.fromEntries([...prev]);
      delete params.carId;
      return params;
    });
  };

  return (
    <div className="cars-page">
      <FiltersSection
        title="Choose a car for rental"
        pickupLocations={pickupLocations}
        dropOffLocations={dropOffLocations}
        categories={categories}
        gearBoxies={gearBoxies}
        fuelTypes={fuelTypes}
        onApplyFilters={handleApplyFilters}
        minPrice={minPrice}
        maxPrice={maxPrice}
      />

      <div className="cars-page__results">
        {loading && <p>Loading cars...</p>}
        {error && <p>Error: {error}</p>}
        {!loading && !error && filteredCarsData.length === 0 && (
          <div className="no-cars-message">
            <p>No cars available for your search.</p>
          </div>
        )}
        {!loading && !error && filteredCarsData.length > 0 && (
          <>
            <div
              className={`cars-page__car-list ${
                isTransitioning ? 'fade-out' : 'fade-in'
              }`}
            >
              {currentItems.map((car) =>
                car.carId ? (
                  <GeneralCarCard
                    key={car.carId}
                    car={car}
                    onDetailsClick={() => handleOpenModal(car)}
                  />
                ) : null,
              )}
            </div>
            <Pagination
              currentPage={currentPage}
              totalPages={totalPages}
              onPageChange={handlePageChange}
            />
          </>
        )}
      </div>

      {selectedCar && (
        <CarDetailsModal car={selectedCar} onClose={handleCloseModal} />
      )}
    </div>
  );
};

export default CarsPage;
