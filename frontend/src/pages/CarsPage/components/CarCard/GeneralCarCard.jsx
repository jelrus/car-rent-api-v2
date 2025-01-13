import PropTypes from 'prop-types';
import CarLocation from '../CarLocation/CarLocation.jsx';
import Rating from '../Rating/Rating.jsx';
import Button from '@components/atoms/Button/Button.jsx';
import { useEffect, useState } from 'react';

import './GeneralCarCard.css';
import { useNavigate } from 'react-router';
import { useSelector } from 'react-redux';

const CarCard = ({ car, onDetailsClick, onShowUnloginModal }) => {
  const isAuth = useSelector((state) => state.auth.token !== null);
  const [isLoggedIn, setIsLoggedIn] = useState(isAuth);
  const [countDays, setCountDays] = useState(0);
  const navigate = useNavigate();

  const { filters } = useSelector((state) => state.cars);

  const newCountDays =
    filters.dropOffDateTime && filters.pickupDateTime
      ? Math.max(
          1,
          Math.ceil(
            (new Date(filters.dropOffDateTime) -
              new Date(filters.pickupDateTime)) /
              (1000 * 60 * 60 * 24),
          ),
        )
      : 1;

  console.log('newCountDays', newCountDays);

  useEffect(() => {
    setCountDays(newCountDays);
  }),
    [filters.dropOffDateTime, filters.pickupDateTime];

  const handleBooking = () => {
    if (!isLoggedIn) {
      onShowUnloginModal();
      return;
    } else {
      navigate(`/booking/${car.carId}`, {
        state: {
          car: {
            carId: car.carId,
            image: car.imageUrl,
            model: car.model,
            location: car.location,
            dropOffId: car.dropOffLocationId,
            pickUpId: car.pickupLocationId,
            deposit: car.deposit,
            pricePerDay: car.pricePerDay,
            totalPrice: car.pricePerDay * countDays,
          },
        },
      });
    }
  };

  const handleDetails = (e) => {
    e.preventDefault();
    onDetailsClick();
  };

  return (
    <div className="car-general-card">
      <div className="car-general-card__img-container">
        <img
          src={car.imageUrl}
          alt={car.model}
          className="car-general-card__imageCar"
        />
        <div className="car-general-card__status">{car.status}</div>
      </div>
      <div className="car-general-card__info">
        <CarLocation model={car.model} location={car.location} />
        <Rating rating={Number(car.rentalExperience)} />
      </div>
      <Button
        text={`Book the car - $${car.pricePerDay}/day`}
        ButtonType="secondary"
        type="button"
        onClick={handleBooking}
      />
      <a href="#" onClick={handleDetails} className="car-card__details-link">
        See more details
      </a>
    </div>
  );
};

CarCard.propTypes = {
  car: PropTypes.shape({
    carId: PropTypes.string.isRequired,
    model: PropTypes.string.isRequired,
    location: PropTypes.string.isRequired,
    rentalExperience: PropTypes.string.isRequired,
    pricePerDay: PropTypes.number.isRequired,
    status: PropTypes.string.isRequired,
    imageUrl: PropTypes.string.isRequired,
    dropOffLocationId: PropTypes.string.isRequired,
    pickupLocationId: PropTypes.string.isRequired,
    deposit: PropTypes.number,
  }).isRequired,
  onDetailsClick: PropTypes.func.isRequired,
  onShowUnloginModal: PropTypes.func.isRequired,
};

export default CarCard;
