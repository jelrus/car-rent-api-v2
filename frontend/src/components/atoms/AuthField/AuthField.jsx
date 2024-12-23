import PropTypes from 'prop-types';
import  './AuthField.css';

const AuthField = ({ id, placeholder, label, value, onChange, underMessage, typeUnderMessage }) => {

  return (
    <div className="input-field">
      <label htmlFor={id}>{label}</label>
      <div className="auth-wrapper">
        <input
          type='text'
          id={id}
          placeholder={placeholder}
          value={value}
          onChange={onChange}
        />
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

AuthField.propTypes = {
  id: PropTypes.string.isRequired,
  placeholder: PropTypes.string.isRequired,
  label: PropTypes.string.isRequired,
  value: PropTypes.string.isRequired,
  onChange: PropTypes.func.isRequired,
  underMessage: PropTypes.string,
  typeUnderMessage: PropTypes.string,
};

export default AuthField;
