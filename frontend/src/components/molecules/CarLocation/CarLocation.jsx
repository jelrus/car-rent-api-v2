import PropTypes from 'prop-types';
import './CarLocation.css';

const CarLocation = ({model, location}) => {
    return (
        <div className='car-location'>
            <h3>{model}</h3>
            <p>{location}</p>
        </div>
    )
}

CarLocation.PropTypes = {
    model: PropTypes.string.isRequired,
    location: PropTypes.string.isRequired
}

export default CarLocation;