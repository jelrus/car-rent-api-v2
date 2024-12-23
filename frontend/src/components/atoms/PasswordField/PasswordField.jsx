import { useState } from 'react';
import PropTypes from 'prop-types';
import  './PasswordField.css';

import eyeClosedIcon from '@assets/Eye closed.svg';
import eyeOpenIcon from '@assets/Eye.svg';

const PasswordField = ({ id, placeholder, label, value, onChange, underMessage, typeUnderMessage }) => {
  const [showPassword, setShowPassword] = useState(false);

  const togglePasswordVisibility = () => {
    setShowPassword((prev) => !prev);
  };

  return (
    <div className="input-field">
      <label htmlFor={id}>{label}</label>
      <div className="password-input-wrapper">
        <input
          type={showPassword ? 'text' : 'password'}
          id={id}
          placeholder={placeholder}
          value={value}
          onChange={onChange}
        />
        <button
          type="button"
          className="toggle-visibility"
          onClick={togglePasswordVisibility}
          onMouseDown={(e) => e.preventDefault()}
          aria-label={showPassword ? 'Hide password' : 'Show password'}
        >
          <img
            src={showPassword ? eyeOpenIcon : eyeClosedIcon}
            alt={showPassword ? 'Hide password' : 'Show password'}
          />
        </button>
      </div>
      {underMessage && (
        <span
          className={`under-message ${typeUnderMessage === 'error' ? 'error' : 'info'}`}
        >
          {underMessage}
        </span>
      )}
    </div>
  );
};

PasswordField.propTypes = {
  id: PropTypes.string.isRequired,
  placeholder: PropTypes.string.isRequired,
  label: PropTypes.string.isRequired,
  value: PropTypes.string.isRequired,
  onChange: PropTypes.func.isRequired,
  underMessage: PropTypes.string,
  typeUnderMessage: PropTypes.string,
};

export default PasswordField;
