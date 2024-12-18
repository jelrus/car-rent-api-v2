import FormField from '@/components/FormField/FormField';
import Button from '@components/atoms/Button/Button';
import './RegistrationForm.css';
import {registerUser} from '@redux/actions/registerActions'
import {useState} from "react"
import { useDispatch, useSelector } from 'react-redux';
import { redirect } from 'react-router';

const RegistrationForm = () => {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
  
    const dispatch = useDispatch();
    const { isAuth, loading } = useSelector((state) => state.register);
    
    const handleSubmit = () => {
      if (!email || !password || !firstName || !lastName) {
        setError('All fields are required');
        return;
      }
      if (!/\S+@\S+\.\S+/.test(email)) {
        setError('Invalid email format.');
        return;
      }
      if (password.length < 6) {
        setError('Password must be at least 6 characters long.');
        return;
      }
      setError('');
      const userData = { email, firstName, lastName, password };
      dispatch(registerUser(userData));
    };
  
    const handleCancel = () => {
      console.log("Cancel");
    };
  
    if (isAuth) {
      return redirect("/home");
    }
  
    return (
      <div className="registration-form">
        <div className="registration-form__title">
          <h2>Create an account</h2>
          <p>Enter your details below to get started</p>
        </div>
  
        <div className="registration-form__block">
          <div className="registration-form__block-name">
            <FormField
              id="name"
              type="text"
              placeholder="Write your name"
              fieldType="input"
              label="Name"
              value={firstName}
              onChange={(e) => setFirstName(e.target.value)}
            />
  
            <FormField
              id="surname"
              type="text"
              placeholder="Write your surname"
              fieldType="input"
              label="Surname"
              value={lastName}
              onChange={(e) => setLastName(e.target.value)}
            />
          </div>
  
          <FormField
            id="email"
            type="email"
            placeholder="Write your email"
            fieldType="input"
            label="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
  
          <FormField
            id="password"
            type="password"
            placeholder="Create password"
            fieldType="password"
            label="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
  
          <div className="registration-form__block-button">
            <Button text="Cancel" type="secondary" onClick={handleCancel} />
            <Button text="Register" type="primary" onClick={handleSubmit} disabled={loading} />
          </div>
  
          {error && <p className="error-message">{error}</p>}
        </div>
      </div>
    );
  };

export default RegistrationForm;
