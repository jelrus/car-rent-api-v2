import Button from '@/components/atoms/Button/Button';
import './CarCard.css';
import PropTypes from 'prop-types';

const CarCard = ({ car, onConfirm }) => {
  return (
    <div className="car-card">
      <img src={car.image} alt={car.name} className="car-card__image" />
      <div className='content'>
      <h3 className='title'>{car.name}</h3>
      <p className='location'>{car.location}</p>
      <hr className="hr"/>
      <div className='total'>
        <span>Total:</span>
        <span>${car.price}</span>
      </div>
      <p className='deposit'>Deposit: ${car.deposit}</p>
      </div>
      <Button type='primary' text='Confirm reservation' onClick={onConfirm}/>
    </div>
  );
};

CarCard.propTypes = {
  car: PropTypes.shape({
    name: PropTypes.string.isRequired,
    location: PropTypes.string.isRequired,
    image: PropTypes.string.isRequired,
    price: PropTypes.number.isRequired,
    deposit: PropTypes.number.isRequired,
  }).isRequired,
  onConfirm: PropTypes.func.isRequired,
};

export default CarCard;
