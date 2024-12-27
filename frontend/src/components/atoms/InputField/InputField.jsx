import PropTypes from 'prop-types';
import './InputField.css';

const InputField = ({ id, type, placeholder, label, onChange,value}) => {
	return (
		<div className='input-field'>
			<label htmlFor={id}>{label}</label>
			<input type={type} id={id} placeholder={placeholder} value={value} onChange={onChange}/>
		</div>
	);
};

InputField.propTypes = {
		id: PropTypes.string.isRequired,
		type: PropTypes.string.isRequired,
		placeholder: PropTypes.string.isRequired,
		label: PropTypes.string.isRequired,
		value: PropTypes.string,
		onChange: PropTypes.func
};

export default InputField;