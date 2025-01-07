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

const CarBookPage = () => {
  const { paramCarId } = useParams();
  const state = useSelector((state) => state);
  console.log(state);
  const { filters } = useSelector((state) => state.cars);
  const carId = paramCarId;
  console.log(filters);

  const location = useLocation();
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const { car } = location.state || {};
  console.log('car', car);
  const user = useSelector((state) => state.auth.user);
  const {
    carDetails,
    bookedDays = [],
    loading: carLoading,
    error: carError,
  } = useSelector((state) => state.carBooked);
  const { loading: bookingLoading, error: bookingError } = useSelector(
    (state) => state.createBooking,
  );

  const isLoading = carLoading || bookingLoading;
  const hasError = carError || bookingError;
  const errorMessage = carError || bookingError;

  const [userInfo] = useState({
    name: user?.username || 'Anastasia Dobrota',
    email: user?.email || 'dobrota@gmail.com',
    phone: user?.phone || '+38 111 111 11 11',
  });

  const getTodayDate = () => {
    const now = new Date();

    // Форматування до локального часу
    const options = {
      year: 'numeric',
      month: '2-digit',
      day: '2-digit',
      hour: '2-digit',
      minute: '2-digit',
      second: '2-digit',
      hour12: false,
    };

    const localeDate = now.toLocaleString('en-GB', options); // en-GB для формату DD/MM/YYYY
    const [datePart, timePart] = localeDate.split(', '); // Розділяємо дату і час

    // Перетворення формату DD/MM/YYYY на YYYY-MM-DD
    const [day, month, year] = datePart.split('/');
    const formattedDate = `${year}-${month}-${day}T${timePart}`;
    return formattedDate;
  };

  const formatDate = (dateString) => {
    const date = new Date(dateString);

    // Дістаємо компоненти дати
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0'); // Місяці починаються з 0
    const day = String(date.getDate()).padStart(2, '0');
    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');

    // Збираємо у формат "YYYY-MM-DD HH:mm"
    return `${year}-${month}-${day} ${hours}:${minutes}`;
  };

  const [bookingInfo, setBookingInfo] = useState({
    pickUp: {
      id: filters?.pickupLocationId,
      location: getLocationName(filters.pickupLocationId),
      dateTime: car?.pickupDateTime || filters?.pickupDateTime || getTodayDate(),
    },
    dropOff: {
      id: filters.dropOffLocationId,
      location: getLocationName(filters.dropOffLocationId),
      dateTime:
        car?.dropOffDateTime || filters.dropOffDateTime || getTodayDate(),
    },
    car: {
      id: carId,
      name: car?.model || carDetails?.model || 'Car Name',
      location: car?.location || carDetails?.location || 'Car Location',
      image: car.image || carDetails?.images?.[0] || image,
      price: car?.totalPrice || car?.pricePerDay || 0,
      deposit: car?.deposit || carDetails?.deposit || 0,
    },
  });
  const [showModal, setShowModal] = useState(false);
  const [modalMessage, setModalMessage] = useState({
    header: '',
    message: '',
  });
  useEffect(() => {
    if (carId) {
      dispatch(getCarDetails(carId));
      dispatch(getBookedDays(carId));
    }
  }, [carId, dispatch]);

  useEffect(() => {
    if (bookedDays.length > 0) {
      const latestBookingDate = bookedDays[bookedDays.length - 1];
      setBookingInfo((prevState) => ({
        ...prevState,
        pickUp: { ...prevState.pickUp, date: latestBookingDate },
        dropOff: { ...prevState.dropOff, date: latestBookingDate },
      }));
    }
  }, [bookedDays]);

  const handleConfirmReservation = async () => {
    const bookingData = {
      carId: bookingInfo.car.id,
      clientId: user.userId,
      dropOffDateTime: formatDate(bookingInfo.dropOff.dateTime),
      pickupDateTime: formatDate(bookingInfo.pickUp.dateTime),
      pickupLocationId: bookingInfo.pickUp.id,
      dropOffLocationId: bookingInfo.dropOff.id,
    };
    try {
      const actionResult = await dispatch(createBooking(bookingData));
      const data = actionResult.payload;
      console.log('data', data);
      if (data.message === 'No locations found or dates are unavailable') {
        setModalMessage({
          header: `Sorry ${userInfo.name}`,
          message: (
            <div className="modal-message-card__message">
              It seems like someone has already reserved this car. You can find
              similar cars{' '}
              <Link className="modal-message-card__message-link" to="/cars">
                here
              </Link>
              .
            </div>
          ),
        });
        setShowModal(true);
        return;
      }

      navigate('/bookings', { state: { message: data.message } });
    } catch (error) {
      setModalMessage({
        header: 'Booking Error',
        message:
          'An error occurred while confirming your booking. Please try again.',
      });
      setShowModal(true);
    }
    // navigate('/bookings', { state: { message: 'data.message' } });
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
        {hasError && <p className="error">Error: {errorMessage}</p>}
        {!isLoading && !hasError && (
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
