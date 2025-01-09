import PropTypes from 'prop-types';
import CarLocation from '@pages/CarsPage/components/CarLocation/CarLocation.jsx';
import Rating from '@pages/CarsPage/components/Rating/Rating.jsx';
import GasStation from '@assets/Gas-station.svg';
import Gearbox from '@assets/manual-gearbox.svg';
import Speedtest from '@assets/brand-speedtest.svg';
import Engine from '@assets/Engine.svg';
import CarFan from '@assets/Car-fan.svg';
import IconMan from '@assets/IconMan.svg';
import Button from '@components/atoms/Button/Button.jsx';
import { useState, useEffect, useRef } from 'react';
import CustomCalendar from '../CustomCalendar/CustomCalendar.jsx';
import { useDispatch, useSelector } from 'react-redux';
import { fetchBookedDays } from '@/redux/slices/carsSlice';
// import { getCarDetails } from '@/redux/slices/carSlice';
import ModalMessageCard from '@/components/atoms/MessageCard/MessageCard';

import './CarDetailsModal.css';
import { useNavigate } from 'react-router';
import UnloginDialog from '@/components/molecules/UnloginDialog/UnloginDialog.jsx';

const feedbacks = [
  {
    userImage: 'path/to/image1.jpg',
    userName: 'Sarah L.',
    rating: 5,
    comment:
      'Fantastic service from start to finish! The booking process was smooth, and the staff was incredibly helpful in answering all my questions. The car was clean, in great condition, and made my trip so much more enjoyable. Highly recommend!',
    date: '2024-11-01',
  },
  {
    userImage: 'path/to/image2.jpg',
    userName: 'Ahmed K.',
    rating: 4,
    comment:
      'The car rental process was seamless, and the car was practically brand new. I loved the variety of vehicles available, and the flexibility with pick-up and drop-off locations made my business trip so much easier. Thank you for the exceptional service!',
    date: '2024-10-28',
  },
  {
    userImage: 'path/to/image3.jpg',
    userName: 'David P.',
    rating: 4.5,
    comment:
      'The best car rental experience I’ve had. Quick and easy booking, excellent customer service, and a well-maintained vehicle. Made my travel in Italy stress-free. I’ll definitely be using them for future trips!',
    date: '2024-10-14',
  },
];

const CarDetailsModal = ({ car, onClose }) => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const isAuth = useSelector((state) => state.auth.token !== null);
  const { carDetails } = useSelector((state) => state.carBooked);
  const { filters } = useSelector((state) => state.cars);

  const calendarRef = useRef(null);
  const [mainImage, setMainImage] = useState(
    car.imageUrl || '/placeholder.jpg',
  );
  const [currentSort, setCurrentSort] = useState('newest');
  const [currentPage, setCurrentPage] = useState(1);

  const [isCalendarVisible, setIsCalendarVisible] = useState(false);
  const [activeField, setActiveField] = useState(null);

  const [isLoggedIn, setIsLoggedIn] = useState(isAuth);
  const [showUnloginDialog, setShowUnloginDialog] = useState(false);

  const [selectedDates, setSelectedDates] = useState({
    pickup: { date: null, time: '07:00AM' },
    dropOff: { date: null, time: '10:00AM' },
  });

  const bookedDays = useSelector(
    (state) => state.cars.bookedDays[car.carId] || [],
  );
  console.log(bookedDays);
  useEffect(() => {
    !isLoggedIn && setShowUnloginDialog(true);
  }, [isLoggedIn]);

  const [showModalMessage, setShowModalMessage] = useState({
    isVisible: false,
    message: '',
  });

  useEffect(() => {
    if (car?.carId) {
      dispatch(fetchBookedDays(car.carId));
    }
  }, [car, dispatch]);

  const formattedBookedDays = bookedDays.map((date) => {
    const [day, month, year] = date.split('.');
    return `${year}-${month}-${day}`;
  });

  useEffect(() => {
    if (!car) return;

    const thumbnails = document.querySelectorAll('.thumbnail-gallery img');
    const handleClick = (e) => {
      setMainImage(e.target.src);
    };

    thumbnails.forEach((thumbnail) => {
      thumbnail.addEventListener('click', handleClick);
    });

    return () => {
      thumbnails.forEach((thumbnail) => {
        thumbnail.removeEventListener('click', handleClick);
      });
    };
  }, [car]);

  if (!car) {
    return <div>Loading...</div>;
  }

  const capitalizeFirstLetter = (text) => {
    if (!text) return 'N/A';
    return text.toLowerCase().replace(/^\w/, (c) => c.toUpperCase());
  };

  const handleBooking = (car) => {
    const formatDateTime = (date, time) => {
      if (!date || !time) return null;

      // Розділення часу на години, хвилини та період (AM/PM)
      const [hours, minutes, period] = time
        .match(/(\d+):(\d+)(AM|PM)/)
        .slice(1);
      const adjustedHours =
        period === 'PM' && hours !== '12'
          ? parseInt(hours) + 12
          : parseInt(hours);

      // Комбінування дати й часу
      const fullDate = new Date(date);
      fullDate.setHours(adjustedHours);
      fullDate.setMinutes(minutes);
      fullDate.setSeconds(0);

      // Форматування у потрібний формат: YYYY-MM-DDTHH:mm:ss
      const year = fullDate.getFullYear();
      const month = String(fullDate.getMonth() + 1).padStart(2, '0'); // Додаємо 0 перед місяцями < 10
      const day = String(fullDate.getDate()).padStart(2, '0');
      const hoursFormatted = String(fullDate.getHours()).padStart(2, '0');
      const minutesFormatted = String(fullDate.getMinutes()).padStart(2, '0');
      const seconds = String(fullDate.getSeconds()).padStart(2, '0');

      return `${year}-${month}-${day}T${hoursFormatted}:${minutesFormatted}:${seconds}`;
    };

    // Форматування обраних дат
    const formattedPickup = formatDateTime(
      selectedDates.pickup.date,
      selectedDates.pickup.time,
    );
    const formattedDropOff = formatDateTime(
      selectedDates.dropOff.date,
      selectedDates.dropOff.time,
    );

    // Обчислення кількості днів
    const countDays =
      formattedPickup && formattedDropOff
        ? Math.max(
            1,
            Math.ceil(
              (new Date(formattedDropOff) - new Date(formattedPickup)) /
                (1000 * 60 * 60 * 24),
            ),
          )
        : 1;

    // Навігація до сторінки бронювання
    navigate(`/booking/${car.carId}`, {
      state: {
        car: {
          id: car.carId,
          image: mainImage,
          model: car.model,
          location: car.location,
          dropOffId: filters.dropOffLocationId,
          pickUpId: filters.pickupLocationId,
          deposit: car.deposit || 0,
          totalPrice: car.pricePerDay * countDays,
          pickupDateTime: formattedPickup,
          dropOffDateTime: formattedDropOff,
        },
      },
    });
  };

  const itemsPerPage = 5;
  const indexOfLastItem = currentPage * itemsPerPage;
  const indexOfFirstItem = indexOfLastItem - itemsPerPage;

  const toggleCalendar = (field) => {
    if (activeField === field && isCalendarVisible) {
      setIsCalendarVisible(false);
      setActiveField(null);
    } else {
      setActiveField(field);
      setIsCalendarVisible(true);
    }
  };

  useEffect(() => {
    const handleOutsideClick = (event) => {
      if (
        calendarRef.current &&
        !calendarRef.current.contains(event.target) &&
        !event.target.closest('.date-picker-field')
      ) {
        setIsCalendarVisible(false);
        setActiveField(null);
      }
    };

    document.addEventListener('mousedown', handleOutsideClick);
    return () => {
      document.removeEventListener('mousedown', handleOutsideClick);
    };
  }, []);

  const sortedFeedbacks = [...feedbacks].sort((a, b) => {
    if (currentSort === 'newest') return new Date(b.date) - new Date(a.date);
    if (currentSort === 'latest') return new Date(a.date) - new Date(b.date);
    if (currentSort === 'ratingLowHigh') return a.rating - b.rating;
    if (currentSort === 'ratingHighLow') return b.rating - a.rating;
    return 0;
  });

  const currentFeedbacks = sortedFeedbacks.slice(
    indexOfFirstItem,
    indexOfLastItem,
  );

  const handleSortChange = (value) => {
    setCurrentSort(value);
    setCurrentPage(1);
  };
  // const handleOverlayClick = (e) => {
  //   if (e.target === e.currentTarget) {
  //     onClose();
  //   }
  // };
  // const handlePageChange = (page) => setCurrentPage(page);

  return (
    <div
      className={`modal-overlay ${car ? 'open' : ''}`}
      onClick={(e) => {
        if (e.target === e.currentTarget) {
          onClose();
        }
      }}
    >
      <div className={`modal-content ${car ? 'open' : ''}`}>
        <button className="modal-close-button" onClick={onClose}>
          &times;
        </button>

        <div className="modal-top">
          <div className="modal-slider">
            <div className="thumbnail-gallery">
              <img src={car.imageUrl} alt="Thumbnail 1" />
              <img src={car.imageUrl} alt="Thumbnail 2" />
              <img src={car.imageUrl} alt="Thumbnail 3" />
              <img src={IconMan} alt="Thumbnail 4" />
              <img src={car.imageUrl} alt="Thumbnail 5" />
            </div>
            <div className="main-image">
              <img src={mainImage || '/placeholder.jpg'} alt="Main Car Image" />
              <div className="modal-card__status">{car.status}</div>
            </div>
          </div>

          <div className="modal-car-info">
            <div className="modal-car__title">
              <CarLocation model={car.model} location={car.location} />
              <Rating rating={car.carRating} />
            </div>

            <div className="details-grid">
              <div className="detail-item">
                <img src={Gearbox} alt="Gearbox" className="icon" />
                <span>
                  {capitalizeFirstLetter(carDetails.gearBoxType || 'N/A')}
                </span>
              </div>
              <div className="detail-item">
                <img src={Engine} alt="Engine" className="icon" />
                <span>{carDetails.engineCapacity || 'N/A'}</span>
              </div>
              <div className="detail-item">
                <img src={GasStation} alt="Fuel" className="icon" />
                <span>
                  {capitalizeFirstLetter(carDetails.fuelType || 'N/A')}
                </span>
              </div>
              <div className="detail-item">
                <img src={IconMan} alt="Seats" className="icon" />
                <span>{carDetails.passengerCapacity || 'N/A'}</span>
              </div>
              <div className="detail-item">
                <img src={Speedtest} alt="Consumption" className="icon" />
                <span>
                  {capitalizeFirstLetter(carDetails.fuelConsumption || 'N/A')}
                </span>
              </div>
              <div className="detail-item">
                <img src={CarFan} alt="Climate control" className="icon" />
                <span>
                  {capitalizeFirstLetter(
                    carDetails.climateControlOption || 'N/A',
                  )}
                </span>
              </div>
            </div>

            <div className="details-date">
              <div className="date-picker-fields">
                <div
                  className={`date-picker-field ${activeField === 'pickup' ? 'active' : ''}`}
                  onClick={(e) => {
                    e.stopPropagation();
                    toggleCalendar('pickup');
                  }}
                >
                  {selectedDates.pickup?.date instanceof Date
                    ? `${selectedDates.pickup.date.toLocaleDateString('en-US', {
                        month: 'short',
                        day: 'numeric',
                      })} ${selectedDates.pickup.time}`
                    : 'Pick-up date'}
                  <span className="dropdown-arrow">&#9662;</span>
                </div>

                <div
                  className={`date-picker-field ${activeField === 'dropOff' ? 'active' : ''}`}
                  onClick={(e) => {
                    e.stopPropagation();
                    toggleCalendar('dropOff');
                  }}
                >
                  {selectedDates.dropOff?.date instanceof Date
                    ? `${selectedDates.dropOff.date.toLocaleDateString(
                        'en-US',
                        {
                          month: 'short',
                          day: 'numeric',
                        },
                      )} ${selectedDates.dropOff.time}`
                    : 'Drop-off date'}
                  <span className="dropdown-arrow">&#9662;</span>
                </div>
              </div>

              {isCalendarVisible && (
                <div
                  className="calendar-wrapper"
                  ref={calendarRef}
                  onClick={(e) => e.stopPropagation()}
                >
                  <CustomCalendar
                    bookedDays={formattedBookedDays}
                    selectedDates={selectedDates}
                    onDateSelect={(field, date) => {
                      setSelectedDates((prev) => ({
                        ...prev,
                        [field]: { ...prev[field], date },
                      }));
                    }}
                    onTimeSelect={(field, time) => {
                      setSelectedDates((prev) => ({
                        ...prev,
                        [field]: { ...prev[field], time },
                      }));
                    }}
                    onShowModalMessage={(message) => {
                      setShowModalMessage({
                        isVisible: true,
                        message,
                      });
                    }}
                  />
                </div>
              )}
            </div>

            <Button
              text={`Book - ${car.pricePerDay || 'N/A'}/day`}
              type="submit"
              ButtonType="primary"
              onClick={() => handleBooking(car)}
              disabled={!isLoggedIn}
            />
          </div>
        </div>

        <div className="feedback-section">
          <div className="feedback-header">
            <h3 className="feedback-title">Feedback</h3>
            <div className="feedback-sort">
              <label htmlFor="sort">Sort by</label>
              <select
                id="sort"
                value={currentSort}
                onChange={(e) => handleSortChange(e.target.value)}
              >
                <option value="newest">The newest</option>
                <option value="latest">The latest</option>
                <option value="ratingLowHigh">Rating: low to high</option>
                <option value="ratingHighLow">Rating: high to low</option>
              </select>
            </div>
          </div>
          <ul className="feedback-list">
            {currentFeedbacks.map((feedback, index) => (
              <li key={index} className="feedback-item">
                <div className="feedback-user">
                  <img
                    src={feedback.userImage}
                    alt={feedback.userName}
                    className="feedback-user-image"
                  />
                  <span className="feedback-user-name">
                    {feedback.userName}
                  </span>
                </div>
                <div className="feedback-content">
                  <div className="feedback-rating">
                    {'⭐'.repeat(feedback.rating)}
                  </div>
                  <p>{feedback.comment}</p>
                </div>
                <div className="feedback-date">{feedback.date}</div>
              </li>
            ))}
          </ul>
        </div>
        {showUnloginDialog && (
          <UnloginDialog onClose={() => setShowUnloginDialog(false)} />
        )}
        {showModalMessage.isVisible && (
          <ModalMessageCard
            message={showModalMessage.message}
            onClose={() =>
              setShowModalMessage({
                isVisible: false,
                message: '',
              })
            }
          />
        )}
      </div>
    </div>
  );
};

CarDetailsModal.propTypes = {
  car: PropTypes.shape({
    carId: PropTypes.string.isRequired,
    model: PropTypes.string.isRequired,
    location: PropTypes.string.isRequired,
    fuelType: PropTypes.string,
    gearBoxType: PropTypes.string,
    engineCapacity: PropTypes.string,
    passengerCapacity: PropTypes.number,
    climateControlOption: PropTypes.bool,
    pricePerDay: PropTypes.number.isRequired,
    images: PropTypes.arrayOf(PropTypes.string),
    fuelConsumption: PropTypes.string,
    carRating: PropTypes.string || PropTypes.number,
    status: PropTypes.string.isRequired,
    imageUrl: PropTypes.string,
    deposit: PropTypes.number,
    dropOffLocationId: PropTypes.string,
    pickupLocationId: PropTypes.string,
  }).isRequired,
  onClose: PropTypes.func.isRequired,
};

export default CarDetailsModal;
