import { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { fetchCars, applyFilters } from '@/redux/slices/carsSlice';
import FiltersSection from '@pages/CarsPage/components/FiltersSection.jsx';
import CarCard from '../../components/molecules/CarCard/CarCard.jsx';
import Pagination from './components/Pagination/Pagination.jsx';
import CarDetailsModal from './components/CarDetailsModal/CarDetailsModal.jsx';

import './CarsPage.css';

const CarsPage = () => {
  const dispatch = useDispatch();

  const {
    // carsData,
    filteredCarsData,
    pickupLocations,
    dropOffLocations,
    categories,
    gearBoxies,
    fuelTypes,
    minPrice,
    maxPrice,
    loading,
    error,
  } = useSelector((state) => state.cars);

  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 16;
  const [isTransitioning, setIsTransitioning] = useState(false);
  const [selectedCar, setSelectedCar] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);

  useEffect(() => {
    dispatch(fetchCars());
  }, [dispatch]);

  const handleApplyFilters = (filters) => {
    dispatch(applyFilters(filters));
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
    setIsModalOpen(true);
  };

  const handleCloseModal = () => {
    setSelectedCar(null);
    setIsModalOpen(false);
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
              {currentItems.map((car) => (
                <CarCard
                  key={car.carId}
                  car={car}
                  onDetailsClick={() => handleOpenModal(car)}
                />
              ))}
            </div>
            <Pagination
              currentPage={currentPage}
              totalPages={totalPages}
              onPageChange={handlePageChange}
            />
          </>
        )}
      </div>

      {isModalOpen && (
        <CarDetailsModal car={selectedCar} onClose={handleCloseModal} />
      )}
    </div>
  );
};

export default CarsPage;
