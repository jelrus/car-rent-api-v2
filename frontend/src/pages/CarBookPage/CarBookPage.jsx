import './CarBookPage.css';
import PersonalCard from './components/PersonalCard/PersonalCard';
import LocationCard from './components/LocationCard/LocationCard';
import CarCard from './components/CarCard/CarCard';
import { useDispatch, useSelector } from 'react-redux';
import { createBooking } from '@/redux/slices/createBookingSlice';
import { getCarDetails, getBookedDays } from '@/redux/slices/carSlice';
import { useState, useEffect } from 'react';
import { useParams, useNavigate, useLocation, Link } from 'react-router';
import image from '@public/imgCars/audi-a6-quattro-2023.jpg';
import ModalMessageCard from '@/components/atoms/MessageCard/MessageCard';
import checkAuthRole from '@/containers/CheckAuthHoc/CheckAuthHoc';
import getLocationName from '@/utils/getLocationName';
import getTodayDate from '@utils/getTodayDate';
import getTodayDatePlusOneDay from '@utils/getTodayDatePlusOneDay';

const CarBookPage = () => {
  const { paramCarId } = useParams();
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const location = useLocation();

  const { filters } = useSelector((state) => state.cars);
  const {
    carDetails,
    bookedDays = [],
    loading: carLoading,
    error: carError,
  } = useSelector((state) => state.carBooked);
  const user = useSelector((state) => state.auth.user);

  const carId = paramCarId;
  const { car } = location.state || {};
  const isLoading = carLoading;
  const errorMessage = carError;

  const [userInfo] = useState({
    name: user?.username || 'Anastasia Dobrota',
    email: user?.email || 'dobrota@gmail.com',
    phone: user?.phone || '+38 111 111 11 11',
  });

  console.log(car?.pickupDateTime, car?.dropOffDateTime);
  const [bookingInfo, setBookingInfo] = useState({
    pickUp: {
      id: filters?.pickupLocationId,
      location: getLocationName(filters?.pickupLocationId),
      dateTime:
        car?.pickupDateTime || filters?.pickupDateTime || getTodayDate(),
    },
    dropOff: {
      id: filters?.dropOffLocationId,
      location: getLocationName(filters?.dropOffLocationId),
      dateTime:
        car?.dropOffDateTime ||
        filters?.dropOffDateTime ||
        getTodayDatePlusOneDay(),
    },
    car: {
      id: carId,
      name: car?.model || carDetails?.model || 'Car Name',
      location: car?.location || carDetails?.location || 'Car Location',
      image: car?.image || carDetails?.images?.[0] || image,
      price: car?.totalPrice || car?.pricePerDay || 0,
      deposit: car?.deposit || carDetails?.deposit || 0,
    },
  });

  const [showModal, setShowModal] = useState(false);
  const [modalMessage, setModalMessage] = useState({ header: '', message: '' });

  useEffect(() => {
    if (carId) {
      dispatch(getCarDetails(carId));
    }
  }, [carId, dispatch]);

  useEffect(() => {
    console.log(filters, carDetails, car)
    setBookingInfo((prevState) => ({
      ...prevState,
      pickUp: {
        ...prevState.pickUp,
        id: filters?.pickupLocationId,
        location: getLocationName(filters?.pickupLocationId),
        dateTime:
          car?.pickupDateTime || filters?.pickupDateTime || getTodayDate(),
      },
      dropOff: {
        ...prevState.dropOff,
        id: filters?.dropOffLocationId,
        location: getLocationName(filters?.dropOffLocationId),
        dateTime:
          car?.dropOffDateTime ||
          filters?.dropOffDateTime ||
          getTodayDatePlusOneDay(),
      },
      car: {
        ...prevState.car,
        name: car?.model || carDetails?.model || 'Car Name',
        location: car?.location || carDetails?.location || 'Car Location',
        image: car?.image || carDetails?.images?.[0] || image,
        price: car?.totalPrice || car?.pricePerDay || 0,
        deposit: car?.deposit || carDetails?.deposit || 0,
      },
    }));
  }, [filters, carDetails, car, image]);

  useEffect(() => {
    if (bookedDays.length > 0) {
      const latestBookingDate = bookedDays[bookedDays.length - 1];
      setBookingInfo((prevState) => ({
        ...prevState,
        pickUp: { ...prevState.pickUp, dateTime: latestBookingDate },
        dropOff: { ...prevState.dropOff, dateTime: latestBookingDate },
      }));
    }
  }, [bookedDays]);

  const formatDate = (dateString) => {
    if (!dateString) return '';
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');
    return `${year}-${month}-${day} ${hours}:${minutes}`;
  };
  const checkBookingData = (bookingData) => {
    for (const key in bookingData) {
      if (!bookingData[key]) {
        return false;
      }
    }
    return true;
  };
  const handleConfirmReservation = async () => {
    const bookingData = {
      carId: bookingInfo.car.id,
      clientId: user?.userId,
      dropOffDateTime: formatDate(bookingInfo.dropOff.dateTime),
      pickupDateTime: formatDate(bookingInfo.pickUp.dateTime),
      pickupLocationId: bookingInfo.pickUp.id,
      dropOffLocationId: bookingInfo.dropOff.id,
    };
    if (!checkBookingData(bookingData)) {
      setModalMessage({
        header: 'Booking Error',
        message: 'Please fill in all the fields.',
      });
      setShowModal(true);
      return;
    }
    try {
      console.log('Sending booking data:', bookingData);
      const actionResult = await dispatch(createBooking(bookingData));

      if (createBooking.fulfilled.match(actionResult)) {
        navigate('/bookings', {
          state: { message: actionResult.payload.message },
        });
      } else {
        // Помилка у відповіді
        console.error('Booking failed:', actionResult.error);
        setModalMessage({
          header: 'Booking Error',
          message: actionResult.payload.message || 'An error occurred during booking.',
        });
        setShowModal(true);
      }
    } catch (error) {
      console.error('Unexpected error during booking:', error);
      setModalMessage({
        header: 'Unexpected Error',
        message: 'An unexpected error occurred. Please try again later.',
      });
      setShowModal(true);
    }
  };

  return (
    <div className="car-book-page">
      <div className="links">
        <Link to="/home">Cars</Link> <span>{'>'}</span>{' '}
        <Link to={`/booking/${carId}`}>Car booking</Link>
      </div>
      <h1>Car Booking</h1>
      <div className="car-book-page__details">
        {isLoading && <p>Loading...</p>}
        {errorMessage && <p className="error">Error: {errorMessage}</p>}
        {!isLoading && !errorMessage && (
          <>
            <div className="user-info">
              <h2>Personal Info</h2>
              <PersonalCard user={userInfo} />
              <h2>Location</h2>
              <LocationCard
                pickUp={bookingInfo.pickUp}
                dropOff={bookingInfo.dropOff}
              />
            </div>
            <CarCard
              car={bookingInfo.car}
              onConfirm={handleConfirmReservation}
            />
          </>
        )}
      </div>

      {showModal && (
        <ModalMessageCard
          header={modalMessage.header}
          message={modalMessage.message}
          onClose={() => setShowModal(false)}
        />
      )}
    </div>
  );
};

export default checkAuthRole(CarBookPage, ['Client']);
