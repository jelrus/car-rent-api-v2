import { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate, Link } from 'react-router-dom';
import { registerUser } from '@redux/slices/authSlice';
import AuthField from '@/components/atoms/AuthField/AuthField';
import Button from '@components/atoms/Button/Button';
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
  const { error, token } = useSelector((state) => state.auth); 
  const navigate = useNavigate();
  useEffect(() => {
    if (token) {
      navigate('/home'); 
    }
  }, [navigate, token]);

  const validateField = (name, value) => {
    let error = '';
    switch (name) {
      case 'firstName':
        if (!value) error = 'First name is required.';
        break;
      case 'lastName':
        if (!value) error = 'Last name is required.';
        break;
      case 'email':
        if (!value) error = 'Email is required.';
        else if (!/\S+@\S+\.\S+/.test(value)) error = 'Invalid email format.';
        break;
      case 'password':
        if (!value) error = 'Password is required.';
        else if (
          value.length < 8 ||
          !/[A-Z]/.test(value) ||
          !/\d/.test(value)
        ) {
          error =
            'Password must be at least 8 characters long with 1 capital letter and 1 digit.';
        }
        break;
      default:
        break;
    }
    return error;
  };

  const validateUserRegistration = () => {
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
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = () => {
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
    <div className='registration-form'>
      <div className='registration-form__title'>
        <h2>Create an account</h2>
        <p>Enter your details below to get started</p>
      </div>

      <div className='registration-form__block'>
        <div className='registration-form__block-name'>
          <AuthField
            id='firstName'
            name='firstName'
            type='text'
            placeholder='Write your name'
            label='First Name'
            value={formData.firstName}
            onChange={handleChange}
            onBlur={() => handleFieldBlur('firstName')}
            underMessage={touchedFields.firstName ? errors.firstName : ''}
            typeUnderMessage='error'
          />

          <AuthField
            id='lastName'
            name='lastName'
            type='text'
            placeholder='Write your surname'
            label='Last Name'
            value={formData.lastName}
            onChange={handleChange}
            onBlur={() => handleFieldBlur('lastName')}
            underMessage={touchedFields.lastName ? errors.lastName : ''}
            typeUnderMessage='error'
          />
        </div>

        <AuthField
          id='email'
          name='email'
          type='email'
          placeholder='Write your email'
          label='Email'
          value={formData.email}
          onChange={handleChange}
          onBlur={() => handleFieldBlur('email')}
          underMessage={touchedFields.email ? errors.email : ''}
          typeUnderMessage='error'
        />

        <AuthField
          id='password'
          name='password'
          type='password'
          placeholder='Create password'
          label='Password'
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

        <div className='registration-form__block-button'>
          <Button text='Cancel' type='secondary' onClick={handleCancel} />
          <Button text='Register' type='primary' onClick={handleSubmit} />
        </div>

        {error && (
          <div className='registration-form__error'>
            <p>{error}</p>
          </div>
        )}

        <div className='registration-form__login-link'>
          <p>Already have an account?</p>
          <Link to='/login'>Log In</Link>
        </div>
      </div>
    </div>
  );
};

export default RegistrationForm;
