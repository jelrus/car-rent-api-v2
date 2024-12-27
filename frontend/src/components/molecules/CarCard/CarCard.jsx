import PropTypes from 'prop-types';
import CarLocation from '../CarLocation/CarLocation.jsx';
import Rating from '../Rating/Rating.jsx';
import Button from '@components/atoms/Button/Button.jsx';

import './CarCard.css';

const CarCard = ({car, onDetailsClick}) => {
    const handleBooking = () => {
        console.log(`Booking car: ${car.model}`);
    };

    // const handleDetails = () => {
    //     console.log(`Viewing details for: ${car.model}`);
    // };

    return (
        <div className='car-card'>
            <div className='car-card__img-container'>
                <img src={car.imageUrl} alt={car.model} className='car-card__image'/>
                <div className='car-card__status'>{car.status}</div>
            </div>
            <div className='car-card__info'>
                <CarLocation model={car.model} location={car.location}/>
                <Rating rating={car.carRating}/>
            </div>
            {/*<Button text={`Book the car - ${car.pricePerDay}/day`} type='secondary'/>*/}
            <Button text={`Book the car - ${car.pricePerDay}/day`} type='secondary' onClick={handleBooking}/>

            {/*<a href='#'>See more details</a>*/}
            {/*<a href='#' onClick={handleDetails}>See more details</a>*/}
            <a href='#' onClick={(e) => {
                e.preventDefault();
                onDetailsClick();
            }}>
                See more details
            </a>

        </div>
    )
}

CarCard.propTypes = {
    car: PropTypes.shape({
        model: PropTypes.string.isRequired,
        location: PropTypes.string.isRequired,
        carRating: PropTypes.number.isRequired,
        pricePerDay: PropTypes.number.isRequired,
        status: PropTypes.string.isRequired,
        imageUrl: PropTypes.string.isRequired,
    }).isRequired,
    onDetailsClick: PropTypes.func.isRequired,
};

export default CarCard;