import PropTypes from 'prop-types';
import './ButtonLink.css';
import { Link } from 'react-router';

const ButtonLink = ({
  text,
  to,
  onClick,
  type = 'primary',
  disabled = false,
}) => {
  const buttonClasses = `button-link button-link--${type} ${disabled ? 'button-link--disabled' : ''}`;

  if (to) {
    return (
      <Link
        to={to}
        className={buttonClasses}
        onClick={disabled ? (e) => e.preventDefault() : onClick}
      >
        {text}
      </Link>
    );
  }

  return (
    <button
      className={buttonClasses}
      onClick={!disabled ? onClick : undefined}
      disabled={disabled}
    >
      {text}
    </button>
  );
};

ButtonLink.propTypes = {
  text: PropTypes.string.isRequired,
  to: PropTypes.string,
  onClick: PropTypes.func,
  type: PropTypes.oneOf(['primary', 'secondary', 'danger']),
  disabled: PropTypes.bool,
};

export default ButtonLink;
