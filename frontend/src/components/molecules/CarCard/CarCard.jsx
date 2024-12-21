import PropTypes from 'prop-types';
import CarLocation from "./CarLocation.jsx";
import Rating from "./Rating"
// import Button from "./Button";

const CarCard = ({car}) => {
    const handleBooking = () => {
        console.log(`Booking car: ${car.model}`);
    };

    const handleDetails = () => {
        console.log(`Viewing details for: ${car.model}`);
    };

    return (
        <div className='car-card'>

        </div>
    )
}
