import ButtonLink from '@/components/atoms/ButtonLink/ButtonLink';
import './LocationCard.css';
import PropTypes from 'prop-types';

// Date formatting utility
const formatToCustomDateTime = (dateTime) => {
  const date = new Date(dateTime);

  const day = date.getDate().toString().padStart(2, '0');
  const month = (date.getMonth() + 1).toString().padStart(2, '0');
  const year = date.getFullYear();
  const hours = date.getHours() % 12 || 12;
  const minutes = date.getMinutes().toString().padStart(2, '0');
  const amPm = date.getHours() >= 12 ? 'PM' : 'AM';

  return `${day}.${month}.${year} | ${hours}:${minutes} ${amPm}`;
};

const LocationCard = ({ pickUp, dropOff }) => {
  const onChange = () => {
    alert('change');
  };

  return (
    <div className="location-card">
      <div className="location-content">
        <div>
          <h3 className="location-header">Pick-up location</h3>
          <p className="location-name">{pickUp.location}</p>
          <p>{formatToCustomDateTime(pickUp.dateTime)}</p>
        </div>

        <div>
          <h3 className="location-header">Drop-off location</h3>
          <p className="location-name">{dropOff.location}</p>
          <p>{formatToCustomDateTime(dropOff.dateTime)}</p>
        </div>
      </div>
      <div>
        <ButtonLink text="Change" onClick={onChange} />
      </div>
    </div>
  );
};

LocationCard.propTypes = {
  pickUp: PropTypes.shape({
    location: PropTypes.string.isRequired,
    dateTime: PropTypes.string.isRequired,
  }).isRequired,
  dropOff: PropTypes.shape({
    location: PropTypes.string.isRequired,
    dateTime: PropTypes.string.isRequired,
  }).isRequired,
};

export default LocationCard;
