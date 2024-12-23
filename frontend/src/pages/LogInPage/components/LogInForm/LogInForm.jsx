import FormField from '@/components/FormField/FormField';
import Button from '@components/atoms/Button/Button';
import { useDispatch, useSelector } from 'react-redux';
import { useState, useEffect } from 'react'
import { loginUser, setAuthError } from '@/redux/features/authSlice';
import './LogInForm.css';
import { Link } from 'react-router';
import { useNavigate } from 'react-router';


const LogInForm = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { token, authError } = useSelector((state) => state.auth);

  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');
  const [errors, setErrors] = useState({});
  const [touchedFields, setTouchedFields] = useState({});
  const [passwordInfoVisible, setPasswordInfoVisible] = useState(true);

  const validateField = (name, value) => {
    let error = '';
    switch (name) {
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
        } else if ( authError
        ) {
          error =
            "The password isn't correct. Check it and try again";
        }
        break;
      default:
        break;
    }
    return error;
  };

  const validateUserLogin = (user) => {
    setPasswordInfoVisible(false);
    const newErrors = {
      email: validateField('email', user.email),
      password: validateField('password', user.password),
    };
    setErrors(newErrors);
    return Object.values(newErrors).every((error) => !error);
  };

  useEffect(() => {
    if (Object.keys(touchedFields).length > 0) {
      validateUserLogin({ email, password });
    }
    if (token) {
      navigate('/home');
    }
  }, [email, password, token, authError]);

  const handleFieldBlur = (field) => {
    setTouchedFields((prev) => ({ ...prev, [field]: true }));
    setErrors((prev) => ({
      ...prev,
      [field]: validateField(field, eval(field)),
    }));
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
    dispatch(setAuthError(null));
  };

  const handleSubmit = (event) => {
    event.preventDefault()

    setTouchedFields({
      email: true,
      password: true,
    });
  
    const user = {email, password };
    if (!validateUserLogin(user)) return;
  
    dispatch(loginUser(user));
  };

  return (
    <div className='login-form'>
      <div className='login-title'>
        <h2>Log in</h2>
        <p>Glad to see you again</p>
      </div>

      <form onSubmit={handleSubmit} className='login-block'>
        <FormField
          label='Email'
          fieldType='input'
          id='email'
          type='email'
          placeholder='Write your email'
          value={email}
          onChange={(event) => setEmail(event.target.value)} 
          onBlur={() => handleFieldBlur('email')}
          underMessage={touchedFields.email ? errors.email : ''}
          typeUnderMessage='error'
        />

        <FormField
          label='Password'
          fieldType='password'
          id='password'
          placeholder='Write your password'
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
        <Button type="submit" text='Login' />
      </form>

      
      <p className='create-account-page'>
        New here? <Link to="/users">Create an account</Link>
      </p>
    </div>
  );
};

export default LogInForm;
