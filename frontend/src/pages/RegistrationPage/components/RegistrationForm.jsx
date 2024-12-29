import AuthField from '@/components/atoms/AuthField/AuthField';
import PasswordField from '@/components/atoms/PasswordField/PasswordField';
import Button from '@components/atoms/Button/Button';
import { registerUser } from '@redux/slices/authSlice';
import { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Link, useNavigate } from 'react-router-dom';
import { logOut } from '@redux/slices/authSlice';
import { setAuthError } from '@/redux/slices/authSlice';
import './RegistrationForm.css';

const RegistrationForm = () => {
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    email: '',
    password: '',
  });
  const [errors, setErrors] = useState({});
  const [touchedFields, setTouchedFields] = useState({});
  const [passwordInfoVisible, setPasswordInfoVisible] = useState(true);

  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { error, token } = useSelector((state) => state.auth);

  useEffect(() => {
    if (Object.keys(touchedFields).length > 0) {
      validateUserRegistration();
    }
    if (token) {
      dispatch(logOut());
      navigate('/success_signup');
    }
  }, [token, touchedFields, error, formData]);

  const validateField = (name, value) => {
    let errorField = '';
    switch (name) {
      case 'firstName':
        if (!value) errorField = 'First name is required.';
        else if (!/^[a-zA-Z]+$/.test(value))
          errorField = 'Only Latin letters are allowed.';
        break;
      case 'lastName':
        if (!value) errorField = 'Last name is required.';
        else if (!/^[a-zA-Z]+$/.test(value))
          errorField = 'Only Latin letters are allowed.';
        break;
      case 'email':
        if (error) {
          errorField = 'Email already exists';
        }
        if (!value) errorField = 'Email is required.';
        else if (!/\S+@\S+\.\S+/.test(value))
          errorField = 'Invalid email format.';
        break;
      case 'password':
        if (!value) errorField = 'Password is required.';
        else if (value.length < 8)
          errorField = 'Password should contain minimum 8 characters.';
        else if (!/[A-Z]/.test(value))
          errorField = 'Password should contain at list 1 capital letter';
        else if (!/[a-z]/.test(value))
          errorField = 'Password should contain at list 1 small letter';
        else if (!/\d/.test(value))
          errorField = 'Password should contain at list 1 digit';
        break;
      default:
        errorField =
          'Password must be at least 8 characters long with 1 capital letter and 1 digit.';
        break;
    }
    return errorField;
  };

  const validateUserRegistration = () => {
    setPasswordInfoVisible(false);
    const newErrors = Object.keys(formData).reduce((acc, field) => {
      acc[field] = validateField(field, formData[field]);
      return acc;
    }, {});
    setErrors(newErrors);
    return Object.values(newErrors).every((error) => !error);
  };

  const handleFieldBlur = (field) => {
    setTouchedFields((prev) => ({ ...prev, [field]: true }));
    setErrors((prev) => ({
      ...prev,
      [field]: validateField(field, formData[field]),
    }));
  };

  const handleChange = (e) => {
    const { name, value } = e.target;

    if (error) {
      dispatch(setAuthError(null));
    }

    setFormData((prevFormData) => ({
      ...prevFormData,
      [name]: value,
    }));
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    setTouchedFields({
      firstName: true,
      lastName: true,
      email: true,
      password: true,
    });

    if (!validateUserRegistration()) return;

    setPasswordInfoVisible(false);
    dispatch(registerUser(formData));
  };

  const handleCancel = () => {
    setFormData({
      firstName: '',
      lastName: '',
      email: '',
      password: '',
    });
    setErrors({});
    setTouchedFields({});
    setPasswordInfoVisible(true);
  };

  return (
    <form className="registration-form" onSubmit={handleSubmit}>
      <div className="registration-form__title">
        <h2>Create an account</h2>
        <p>Enter your details below to get started</p>
      </div>

      <div className="registration-form__block" onSubmit={handleSubmit}>
        <div className="registration-form__block-name">
          <AuthField
            id="firstName"
            name="firstName"
            type="text"
            placeholder="Write your name"
            label="First Name"
            value={formData.firstName}
            onChange={handleChange}
            onBlur={() => handleFieldBlur('firstName')}
            underMessage={touchedFields.firstName ? errors.firstName : ''}
            typeUnderMessage="error"
          />

          <AuthField
            id="lastName"
            name="lastName"
            type="text"
            placeholder="Write your surname"
            label="Last Name"
            value={formData.lastName}
            onChange={handleChange}
            onBlur={() => handleFieldBlur('lastName')}
            underMessage={touchedFields.lastName ? errors.lastName : ''}
            typeUnderMessage="error"
          />
        </div>

        <AuthField
          id="email"
          name="email"
          type="email"
          placeholder="Write your email"
          label="Email"
          value={formData.email}
          onChange={handleChange}
          onBlur={() => handleFieldBlur('email')}
          underMessage={touchedFields.email ? errors.email : ''}
          typeUnderMessage="error"
        />

        <PasswordField
          id="password"
          name="password"
          type="password"
          placeholder="Create password"
          label="Password"
          value={formData.password}
          onChange={handleChange}
          onBlur={() => handleFieldBlur('password')}
          underMessage={
            touchedFields.password
              ? errors.password
              : passwordInfoVisible
                ? 'Password must be at least 8 characters long with 1 capital letter and 1 digit.'
                : ''
          }
          typeUnderMessage={passwordInfoVisible ? 'info' : 'error'}
        />

        <div className="registration-form__block-button" onReset={handleCancel}>
          <Button
            text="Cancel"
            type="reset"
            ButtonType="secondary"
            onClick={handleCancel}
          />
          <Button text="Register" type="submit" ButtonType="primary" />
        </div>

        <div className="registration-form__login-link">
          <p>
            Already have an account? <Link to="/login">Log In</Link>
          </p>
        </div>
      </div>
    </form>
  );
};

export default RegistrationForm;
