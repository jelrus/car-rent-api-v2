import PropTypes from 'prop-types';
import starImage from '@assets/Star1.svg';
import './Rating.css';

const Rating = ({ rating }) => {
    return (
        <div className="rating">
            <span className="rating-value">{rating}</span>
            <img src={starImage} alt={starImage} className='rating-star'/>
            {/*<CarLocation model={car.model} location={car.location}/>*/}
            {/*<Rating rating={car.carRating}/>*/}
            {/*<div className="car-price">Book the car - ${car.pricePerDay}/day</div>*/}
            {/*<Button text="Book the car" onClick={handleBooking}/>*/}
            {/*<Button text="See more details" onClick={handleDetails}/>*/}
        </div>
    );
};

Rating.propTypes = {
    rating: PropTypes.number.isRequired,
};

export default Rating;