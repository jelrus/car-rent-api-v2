import './SuccessInfoMessage.css';
import congratsIcon from '@/assets/congrats-icon.png';

import PropTypes from 'prop-types';

const SuccessInfoMessage = ({ message, onClick }) => {
  return (
    <div className="success-info__block">
      <img className="success-info__icon" src={congratsIcon} alt="" />
      <div className="success-info__content">
        <p className="success-info__title">Congratulations</p>
        <span className="success-info__message">{message}</span>
      </div>
      <button type="button" className="success-info__close" onClick={onClick}>
        &#x2715;
      </button>
    </div>
  );
};

SuccessInfoMessage.propTypes = {
  message: PropTypes.string.isRequired,
  onClick: PropTypes.func.isRequired,
};

export default SuccessInfoMessage;
