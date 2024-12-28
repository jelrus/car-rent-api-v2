import PropTypes from 'prop-types';
import './Button.css';

const Button = ({ text, onClick, type, ButtonType, disabled }) => {
  return (
    <button
      className={`custom-button custom-button--${ButtonType}`}
      onClick={onClick}
      disabled={disabled}
      type={type}
    >
      {text}
    </button>
  );
};

Button.propTypes = {
  text: PropTypes.string.isRequired,
  onClick: PropTypes.func,
  type: PropTypes.oneOf(['reset', 'submit', 'button']),
  ButtonType: PropTypes.oneOf(['primary', 'secondary']),
  disabled: PropTypes.bool,
};

export default Button;
