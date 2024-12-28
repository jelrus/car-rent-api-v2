import PropTypes from 'prop-types';
import CarLocation from '../CarLocation/CarLocation.jsx';
import Rating from '../Rating/Rating.jsx';
import Button from '@components/atoms/Button/Button.jsx';

import './CarCard.css';
import { useNavigate } from 'react-router';

const CarCard = ({ car, onDetailsClick }) => {
  const navigate = useNavigate();

  const handleBooking = () => {
    navigate(`/booking/${car.carId}`, {
      state: {
        car: {
          id: car.carId,
          image: car.imageUrl,
          model: car.model,
          location: car.location,
          dropOffId: car.dropOffLocationId,
          pickUpId: car.pickupLocationId,
          deposit: car.deposit,
          totalPrice: car.pricePerDay,
        },
      },
    });
  };

  const handleDetails = (e) => {
    e.preventDefault();
    onDetailsClick();
  };

  return (
    <div className="car-card">
      <div className="car-card__img-container">
        <img src={car.imageUrl} alt={car.model} className="car-card__image" />
        <div className="car-card__status">{car.status}</div>
      </div>
      <div className="car-card__info">
        <CarLocation model={car.model} location={car.location} />
        <Rating rating={car.carRating} />
      </div>
      <Button
        text={`Book the car - $${car.pricePerDay}/day`}
        type="secondary"
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
    carRating: PropTypes.number.isRequired,
    pricePerDay: PropTypes.number.isRequired,
    status: PropTypes.string.isRequired,
    imageUrl: PropTypes.string.isRequired,
    dropOffLocationId: PropTypes.string.isRequired,
    pickupLocationId: PropTypes.string.isRequired,
    deposit: PropTypes.number.isRequired,
  }).isRequired,
  onDetailsClick: PropTypes.func.isRequired,
};

export default CarCard;
