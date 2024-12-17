import { useState } from 'react';
import PropTypes from 'prop-types';
import './InputField.css';

import eyeClosedIcon from '../../assets/Eye closed.svg';
import eyeOpenIcon from '../../assets/Eye.svg';

const PasswordField = ({ id, placeholder, label }) => {
	const [showPassword, setShowPassword] = useState(false);
	const [isFocused, setIsFocused] = useState(false);

	const togglePasswordVisibility = () => {
		setShowPassword(!showPassword);
	};

	const handleFocus = () => {
		setIsFocused(true);
	};

	const handleBlur = () => {
		setIsFocused(false);
	};

	return (
		<div className='input-field'>
			<label htmlFor={id}>{label}</label>
			<div className='password-input-wrapper'>
				<input
					type={showPassword ? 'text' : 'password'}
					id={id}
					placeholder={placeholder}
					onFocus={handleFocus}
					onBlur={handleBlur}
				/>
				{isFocused && (
					<button
						type='button'
						className='toggle-visibility'
						onClick={togglePasswordVisibility}
						onMouseDown={(e) => e.preventDefault()}
					>
						<img
							src={showPassword ? eyeOpenIcon : eyeClosedIcon}
							alt={showPassword ? 'Hide password' : 'Show password'}
						/>
					</button>
				)}
			</div>
      <span>Minimum 8 characters with at least 1 capital letter and 1 digit</span>
		</div>
	);
};

PasswordField.propTypes = {
	id: PropTypes.string.isRequired,
	placeholder: PropTypes.string.isRequired,
	label: PropTypes.string.isRequired,
};

export default PasswordField;
