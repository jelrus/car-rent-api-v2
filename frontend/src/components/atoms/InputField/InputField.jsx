import PropTypes from 'prop-types';
import './InputField.css';

const InputField = ({ id, type, placeholder, label }) => {
	return (
		<div className='input-field'>
			<label htmlFor={id}>{label}</label>
			<input type={type} id={id} placeholder={placeholder} />
		</div>
	);
};

InputField.propTypes = {
	id: PropTypes.string.isRequired,
	type: PropTypes.string.isRequired,
	placeholder: PropTypes.string.isRequired,
	label: PropTypes.string.isRequired,
};

export default InputField;
