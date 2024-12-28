import PropTypes from 'prop-types';
import './Button.css';

const Button = ({ text, onClick, type, disabled }) => {
  return (
    <button
      className={`custom-button custom-button--${type}`}
      onClick={onClick}
      disabled={disabled}
    >
      {text}
    </button>
  );
};

Button.propTypes = {
  text: PropTypes.string.isRequired,
  onClick: PropTypes.func,
  type: PropTypes.oneOf(['primary', 'secondary']),
  disabled: PropTypes.bool,
};

export default Button;
