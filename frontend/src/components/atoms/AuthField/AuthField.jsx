import PropTypes from 'prop-types';
import './AuthField.css';

const AuthField = ({ id, name, placeholder, label, value, onChange, onBlur, underMessage, typeUnderMessage }) => {
  return (
    <div className="input-field">
      <label htmlFor={id}>{label}</label>
      <div className="auth-wrapper">
        <input
          type="text"
          id={id}
          name={name}
          placeholder={placeholder}
          value={value}
          onChange={onChange}
          onBlur={onBlur}
          className={underMessage && typeUnderMessage === 'error' ? 'error' : ''}
          aria-invalid={underMessage && typeUnderMessage === 'error'}
        />
      </div>
      {underMessage && (
        <span
          className={`under-message ${
            typeUnderMessage === 'error' ? 'error' : 'info'
          }`}
        >
          {underMessage}
        </span>
      )}
    </div>
  );
};

AuthField.propTypes = {
  id: PropTypes.string.isRequired,
  name: PropTypes.string.isRequired,
  placeholder: PropTypes.string.isRequired,
  label: PropTypes.string.isRequired,
  value: PropTypes.string.isRequired,
  onChange: PropTypes.func.isRequired,
  onBlur: PropTypes.func,
  underMessage: PropTypes.string,
  typeUnderMessage: PropTypes.oneOf(['error', 'info']),
};

export default AuthField;
