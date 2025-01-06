import PropTypes from 'prop-types';
import './SelectField.css';

const SelectField = ({
  id,
  label,
  name,
  value,
  onChange,
  options,
  className,
}) => {
  return (
    <div className={`select-field ${className}`}>
      <label htmlFor={id}>{label}</label>
      <select id={id} name={name} value={value} onChange={onChange}>
        <option value="">Select</option>
        {options.map((option) => (
          <option key={option.id} value={option.id}>
            {option.label}
          </option>
        ))}
      </select>
    </div>
  );
};

SelectField.propTypes = {
  id: PropTypes.string.isRequired,
  label: PropTypes.string.isRequired,
  name: PropTypes.string.isRequired,
  value: PropTypes.string.isRequired,
  onChange: PropTypes.func.isRequired,
  options: PropTypes.arrayOf(PropTypes.object).isRequired,
  className: PropTypes.string,
};

export default SelectField;
