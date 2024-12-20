import AuthField from '@/components/atoms/AuthField/AuthField';
import Button from '@components/atoms/Button/Button';
import { registerUser } from '@redux/actions/registerActions';
import { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import './RegistrationForm.css';
// import { useNavigate } from 'react-router';
import { Link } from 'react-router';
const RegistrationForm = () => {
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [errors, setErrors] = useState({});
  const [touchedFields, setTouchedFields] = useState({});
  const [passwordInfoVisible, setPasswordInfoVisible] = useState(true);

  const dispatch = useDispatch();
  const { isAuth, loading } = useSelector((state) => state.register);
  // const navigate = useNavigate();

  /*
  useEffect(() => {
    if (isAuth) {
      navigate('/home');
    }
  }, [isAuth, navigate]);
  */

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

  const validateUserRegistration = (user) => {
    setPasswordInfoVisible(false);
    const newErrors = {
      firstName: validateField('firstName', user.firstName),
      lastName: validateField('lastName', user.lastName),
      email: validateField('email', user.email),
      password: validateField('password', user.password),
    };
    setErrors(newErrors);
    return Object.values(newErrors).every((error) => !error);
  };

  useEffect(() => {
    if (Object.keys(touchedFields).length > 0) {
      validateUserRegistration({ firstName, lastName, email, password });
    }
  }, [firstName, lastName, email, password]);

  const handleFieldBlur = (field) => {
    setTouchedFields((prev) => ({ ...prev, [field]: true }));
    setErrors((prev) => ({
      ...prev,
      [field]: validateField(field, eval(field)),
    }));
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
    if (passwordInfoVisible) {
      setPasswordInfoVisible(false);
    }
  };

  const handleSubmit = () => {
    setTouchedFields({
      firstName: true,
      lastName: true,
      email: true,
      password: true,
    });
  
    const user = { firstName, lastName, email, password };
    if (!validateUserRegistration(user)) return;
  
    dispatch(registerUser(user));
  };

  const handleCancel = () => {
    setFirstName('');
    setLastName('');
    setEmail('');
    setPassword('');
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
            type='text'
            placeholder='Write your name'
            fieldType='input'
            label='First Name'
            value={firstName}
            onChange={(e) => setFirstName(e.target.value)}
            onBlur={() => handleFieldBlur('firstName')}
            underMessage={touchedFields.firstName ? errors.firstName : ''}
            typeUnderMessage='error'
          />

          <AuthField
            id='lastName'
            type='text'
            placeholder='Write your surname'
            fieldType='input'
            label='Last Name'
            value={lastName}
            onChange={(e) => setLastName(e.target.value)}
            onBlur={() => handleFieldBlur('lastName')}
            underMessage={touchedFields.lastName ? errors.lastName : ''}
            typeUnderMessage='error'
          />
        </div>

        <AuthField
          id='email'
          type='email'
          placeholder='Write your email'
          fieldType='input'
          label='Email'
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          onBlur={() => handleFieldBlur('email')}
          underMessage={touchedFields.email ? errors.email : ''}
          typeUnderMessage='error'
        />

        <AuthField
          id='password'
          type='password'
          placeholder='Create password'
          fieldType='password'
          label='Password'
          value={password}
          onChange={handlePasswordChange}
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
          <Button
            text='Register'
            type='primary'
            onClick={handleSubmit}
            disabled={loading}
          />
        </div>
        <div className="registration-form__login-link">
          <p>Already have an account? {/*<Link to="/login">Log In</Link>*/}
          <a href="/login">Log In</a></p>

        </div>
      </div>
    </div>
  );
};

export default RegistrationForm;
