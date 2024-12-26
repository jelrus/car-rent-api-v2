import './CarBookPage.css';
import PersonalCard from './components/PersonalCard/PersonalCard';
import LocationCard from './components/LocationCard/LocationCard';
import CarCard from './components/CarCard/CarCard';
import { useDispatch, useSelector } from 'react-redux';
import { createBooking } from '@/redux/slices/createBookingSlice';
import { getCarDetails, getBookedDays } from '@/redux/slices/carSlice';
import { useState, useEffect } from 'react';
import { useParams, useNavigate, Link } from 'react-router';
import image from '@public/imgCars/audi-a6-quattro-2023.jpg';
const CarBookPage = () => {
  const { carId: paramCarId } = useParams();
  const carId = paramCarId /*|| 1*/;

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const s = useSelector((state) => state);
  console.log(s);
  const user = useSelector((state) => state.auth.user);
  const {
    carDetails,
    bookedDays,
    loading: carLoading,
    error: carError,
  } = useSelector((state) => state.carBooked);
  const { loading: bookingLoading, error: bookingError } = useSelector(
    (state) => state.createBooking
  );

  const isLoading = carLoading || bookingLoading;
  const hasError = carError || bookingError;
  const errorMessage = carError || bookingError;

  const [userInfo] = useState({
    name: user?.username || 'Anastatia Dobrota',
    email: user?.email || 'dobrota@gmail.com',
    phone: '+38 111 111 11 11',
  });

  const [bookingInfo, setBookingInfo] = useState({
    pickUp: {
      location: 'Kyiv Hyatt Hotel',
      date: '',
      time: '10:00 AM',
    },
    dropOff: {
      location: 'Kyiv Hyatt Hotel',
      date: '',
      time: '10:00 AM',
    },
    car: {
      id: carId,
      name: carDetails?.model || 'Car Name',
      location: carDetails?.location || 'Car Location',
      image: carDetails?.images ? carDetails.images[0] : image,
      price: carDetails?.pricePerDay || 0,
      deposit: carDetails?.deposit || 0,
    },
  });

  useEffect(() => {
    if (carId) {
      dispatch(getCarDetails(carId));
      dispatch(getBookedDays(carId));
    }
  }, [carId, dispatch]);

  useEffect(() => {
    if (bookedDays.length > 0) {
      const availablePickUpDate = bookedDays[bookedDays.length - 1];
      const availableDropOffDate = bookedDays[bookedDays.length - 1];
      setBookingInfo((prevState) => ({
        ...prevState,
        pickUp: {
          ...prevState.pickUp,
          date: availablePickUpDate,
        },
        dropOff: {
          ...prevState.dropOff,
          date: availableDropOffDate,
        },
      }));
    }
  }, [bookedDays]);

  const handleConfirmReservation = async () => {
    const bookingData = {
      carId: bookingInfo.car.id,
      clientId: user?.id || 'f47ac10b-58cc-4372-a567-0e02b2c3d479',
      dropOffDateTime: `${bookingInfo.dropOff.date} ${bookingInfo.dropOff.time}`,
      dropOffLocationId: '9b903ebf-2b18-4946-bc58-045d86a2632e',
      pickupDateTime: `${bookingInfo.pickUp.date} ${bookingInfo.pickUp.time}`,
      pickupLocationId: '9b903ebf-2b18-4946-bc58-045d86a2632e',
    };
  
    try {
      const actionResult = await dispatch(createBooking(bookingData));
      const data = actionResult.payload;
  
      if (data && data.message) {
        navigate('/bookings', { state: { message: data.message } });
      } else {
        console.error('No message received in the booking response.');
      }
    } catch (error) {
      console.error('Error confirming reservation:', error);
    }
  };
  

  return (
    <div className='car-book-page'>
      <div className='links'>
        <Link to={'/home'}>Cars</Link> <span>{'>'}</span> <Link to={`/booking/${carId}`}>Car booking</Link>
      </div>
      <h1>Car Booking</h1>
      <div className='car-book-page__details'>
        {isLoading && <p>Loading...</p>}
        {hasError && <p className='error'>Error: {errorMessage}</p>}
        {!isLoading && !hasError && (
          <>
            <div className='user-info'>
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
    </div>
  );
};

export default CarBookPage;
