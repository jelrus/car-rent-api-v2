import { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { fetchCars, setCurrentPage } from '@/redux/slices/carsSlice';
import FiltersSection from '@pages/CarsPage/components/FiltersSection/FiltersSection.jsx';
import GeneralCarCard from '@pages/CarsPage/components/CarCard/GeneralCarCard.jsx';
import Pagination from './components/Pagination/Pagination.jsx';
import CarDetailsModal from './components/CarDetailsModal/CarDetailsModal.jsx';
import { useSearchParams } from 'react-router-dom';
import { getCarDetails, getBookedDays } from '@/redux/slices/carSlice';
import UnloginDialog from '@/components/molecules/UnloginDialog/UnloginDialog.jsx';

import './CarsPage.css';

const CarsPage = () => {
  const [searchParams, setSearchParams] = useSearchParams();
  const dispatch = useDispatch();
  const { carsData, totalPages, currentPage, loading, error, filters } =
    useSelector((state) => state.cars);

  const isAuth = useSelector((state) => state.auth.token !== null);

  const [selectedCar, setSelectedCar] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [showUnloginDialog, setShowUnloginDialog] = useState(false);

  useEffect(() => {
    dispatch(fetchCars({ filters }));
  }, []);

  // useEffect(() => {
  //   const params = Object.fromEntries([...searchParams]);
  //   const parsedFilters = {
  //     ...params,
  //     priceRange: params.priceRange
  //       ? params.priceRange.split(',').map(Number)
  //       : [minPrice, maxPrice],
  //   };

  //   dispatch(fetchCars(parsedFilters));
  //   dispatch(setFilters(parsedFilters));
  // }, [dispatch, searchParams, minPrice, maxPrice]);

  useEffect(() => {
    const params = Object.fromEntries([...searchParams]);

    if (!carsData || carsData.length === 0) {
      setSelectedCar(null);
      return;
    }

    if (params.carId) {
      const selected = carsData.find((car) => car.carId === params.carId);
      setSelectedCar(selected || null);
    }
  }, [searchParams, carsData]);

  const handlePageChange = (page) => {
    dispatch(setCurrentPage(page));
    dispatch(fetchCars({ filters, page }));
  };

  const handleOpenModal = async (car) => {
    try {
      await dispatch(getCarDetails(car.carId)).unwrap();
      await dispatch(getBookedDays(car.carId));
      setSelectedCar(car);
      setIsModalOpen(true);
      setSearchParams((prevParams) => {
        const newParams = new URLSearchParams(prevParams);
        newParams.set('carId', car.carId);
        return newParams;
      });
    } catch (error) {
      console.error('Failed to fetch car details:', error);
    }
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setSelectedCar(null);

    setSearchParams((prevParams) => {
      const newParams = new URLSearchParams(prevParams);
      newParams.delete('carId');
      return newParams;
    });
  };

  return (
    <div className="cars-page">
      <FiltersSection />

      <div className="cars-page__results">
        {loading && <p>Loading cars...</p>}
        {error && <p>Error: {error}</p>}
        {!loading && !error && carsData.length === 0 && (
          <div className="no-cars-message">
            <p>No cars available for your search.</p>
          </div>
        )}
        {!loading && !error && carsData.length > 0 && (
          <>
            <div className="cars-page__car-list">
              {carsData.map((car) =>
                car.carId ? (
                  <GeneralCarCard
                    key={car.carId}
                    car={car}
                    onShowUnloginModal={() => setShowUnloginDialog(true)}
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

      {isModalOpen && (
        <CarDetailsModal car={selectedCar} onClose={handleCloseModal} />
      )}
      {showUnloginDialog && (
        <UnloginDialog onClose={() => setShowUnloginDialog(false)} />
      )}
    </div>
  );
};

export default CarsPage;
