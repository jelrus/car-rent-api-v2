import ButtonLink from '@/components/atoms/ButtonLink/ButtonLink';
import './LocationCard.css';
import PropTypes from 'prop-types';

const LocationCard = ({ pickUp, dropOff }) => {
  const onChange=()=>{
    alert('change')
  }
  return (
    <div className='location-card'>
      <div className='location-content'>
        <div>
          <h3 className='location-header'>Pick-up location</h3>
          <p className='location-name'>{pickUp.location}</p>
          <p>
            {pickUp.date} | {pickUp.time}
          </p>
        </div>

        <div>
          <h3 className='location-header'>Drop-off location</h3>
          <p className='location-name'>{dropOff.location}</p>
          <p>
            {dropOff.date} | {dropOff.time}
          </p>
        </div>
      </div>
      <div>
        <ButtonLink text='Change' onClick={onChange}/>
      </div>
    </div>
  );
};

LocationCard.propTypes = {
  pickUp: PropTypes.shape({
    location: PropTypes.string.isRequired,
    date: PropTypes.string.isRequired,
    time: PropTypes.string.isRequired,
  }).isRequired,
  dropOff: PropTypes.shape({
    location: PropTypes.string.isRequired,
    date: PropTypes.string.isRequired,
    time: PropTypes.string.isRequired,
  }).isRequired
};

export default LocationCard;
