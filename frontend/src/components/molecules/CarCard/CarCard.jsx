import PropTypes from 'prop-types';
import CarLocation from '../CarLocation/CarLocation.jsx';
import Rating from '../Rating/Rating.jsx';
import Button from '../../atoms/Button/Button.jsx';

import './CarCard.css';

const CarCard = ({car}) => {
    // const handleBooking = () => {
    //     console.log(`Booking car: ${car.model}`);
    // };

    const handleDetails = () => {
        console.log(`Viewing details for: ${car.model}`);
    };

    return (
        <div className='car-card'>
            <div className='car-card__img-container'>
                <img src={car.imageUrl} alt={car.model} className='car-card__image'/>
                <div className='car-card__status'>{car.status}</div>
            </div>
            <div className='car-card__info'>
                <CarLocation model={car.model} location={car.location} />
                <Rating rating={car.carRating} />
            </div>
            <Button text={`Book the car - ${car.pricePerDay}/day`} type='secondary' />

            {/*<Button text='Book the car' onClick={handleBooking} />*/}
            <a href='#'>See more details</a>
            {/*<Button text='See more details' onClick={handleDetails} />*/}
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
};

export default CarCard;