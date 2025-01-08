import PropTypes from 'prop-types';
import './SelectField.css';
import Select from 'react-select';

const SelectField = ({
  id,
  label,
  name,
  value,
  onChange,
  options,
  className,
}) => {
  const customStyles = {
    control: (base, state) => ({
      ...base,
      border: state.isFocused ? '2px solid #000' : '1px solid #ccc',
      borderRadius: '8px',
      backgroundColor: '#FFFBF3',
      padding: '5px',
      fontSize: '16px',
      color: '#000000',
      width: '100%',
      boxSizing: 'border-box',
      boxShadow: 'none',
      outline: 'none',
      ':hover': {
        border: state.isFocused ? '2px solid #000' : '1px solid #ccc',
      },
    }),
    menu: (base) => ({
      ...base,
      margin: '0',
      width: '100%',
      boxSizing: 'border-box',
      borderRadius: '8px',
      backgroundColor: '#FFFBF3',
      lineHeight: '20px',
      padding: '16px',
      outline: 'none',
      zIndex: 3,
    }),
    menuList: (base) => ({
      ...base,
      maxHeight: '264px',
      overflowY: 'auto',
      scrollbarWidth: 'thin',
      scrollbarColor: '#ccc #FFFBF3',
    }),
    option: (base, { isFocused }) => ({
      ...base,
      backgroundColor: isFocused ? '#000' : '#FFFBF3',
      color: isFocused ? '#FFFBF3' : '#000',
      cursor: 'pointer',
      borderRadius: '4px',
      boxSizing: 'border-box',
      ':active': {
        backgroundColor: '#000',
        color: '#FFFBF3',
      },
    }),
    placeholder: (base) => ({
      ...base,
      color: '#000',
      fontSize: '16px',
      fontWeight: '400',
    }),
    indicatorSeparator: () => ({
      display: 'none',
    }),
  };

  const formattedOptions = options.map((option) => ({
    value: option.id,
    label: option.label,
  }));

  const handleChange = (selectedOption) => {
    onChange({
      target: { name, value: selectedOption ? selectedOption.value : '' },
    });
  };

  const selectedOption =
    formattedOptions.find((option) => option.value === value) || null;

  return (
    <div className={`select-field ${className}`}>
      <label htmlFor={id}>{label}</label>
      <Select
        inputId={id}
        name={name}
        value={selectedOption}
        onChange={handleChange}
        options={formattedOptions}
        styles={customStyles}
        placeholder="Select"
      />
    </div>
  );
};

SelectField.propTypes = {
  id: PropTypes.string.isRequired,
  label: PropTypes.string.isRequired,
  name: PropTypes.string.isRequired,
  value: PropTypes.string,
  onChange: PropTypes.func.isRequired,
  options: PropTypes.arrayOf(PropTypes.object).isRequired,
  className: PropTypes.string,
};

export default SelectField;
