import FormField from '@/components/FormField/FormField';
import { loginUser } from '@/redux/slices/authSlice';
import Button from '@components/atoms/Button/Button';
import { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Link, useNavigate } from 'react-router';
import { setAuthError } from '@/redux/slices/authSlice';
import './LogInForm.css';

const LogInForm = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { token, error, loading } = useSelector((state) => state.auth);

  const [formData, setFormData] = useState({
    email: '',
    password: '',
  });
  const [errors, setErrors] = useState({});
  const [touchedFields, setTouchedFields] = useState({});
  const [passwordInfoVisible, setPasswordInfoVisible] = useState(true);

  useEffect(() => {
    if (Object.keys(touchedFields).length > 0) {
      validateUserLogin();
    }
    if (token) {
      navigate('/home');
    }
  }, [touchedFields, token, error, formData]);

  const validateField = (name, value) => {
    let errorField = '';
    switch (name) {
      case 'email':
        if (!value) {
          errorField = 'Email is required.';
        } else if (!/\S+@\S+\.\S+/.test(value)) {
          errorField = 'Invalid email format.';
        }
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
        else if (error) {
          errorField = `The password or email isn't correct. Check it and try again`;
        }
        break;
      default:
        break;
    }
    return errorField;
  };

  const validateUserLogin = () => {
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
      email: true,
      password: true,
    });
    if (!validateUserLogin()) return;
    dispatch(loginUser(formData));
  };

  return (
    <form className="login-form" onSubmit={handleSubmit}>
      <div className="login-title">
        <h2>Log in</h2>
        <p>Glad to see you again</p>
      </div>

      <div className="login-block">
        <FormField
          label="Email"
          fieldType="input"
          name="email"
          id="email"
          type="email"
          placeholder="Write your email"
          value={formData.email}
          onChange={handleChange}
          onBlur={() => handleFieldBlur('email')}
          underMessage={touchedFields.email ? errors.email : ''}
          typeUnderMessage="error"
        />

        <FormField
          label="Password"
          fieldType="password"
          name="password"
          type="password"
          id="password"
          placeholder="Write your password"
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
        <Button
          text="Login"
          ButtonType="primary"
          type="submit"
          disabled={loading}
        />
      </div>

      <div>
        <p className="login-form__sighup-link">
          New here? <Link to="/signup">Create an account</Link>
        </p>
      </div>
    </form>
  );
};

export default LogInForm;
