import Banner from '@assets/Banner.png';
import './LogInPage.css';
import LogInForm from './components/LogInForm/LogInForm';
import Loader from '@/components/atoms/Loader/Loader';
import { useSelector } from 'react-redux';
import { useEffect, useState } from 'react';
import SuccessInfoMessage from '@/components/molecules/SuccessInfoMessage/SuccessInfoMessage';
import { useLocation } from 'react-router-dom';

const LogInPage = () => {
  const location = useLocation();

  const { loading } = useSelector((state) => state.auth);
  const { message } = location.state || {};
  const [successMessage, setSuccessMessage] = useState('');

  const handleCloseInfoMessage = () => {
    setSuccessMessage('');
  };

  useEffect(() => {
    if (message) {
      setSuccessMessage(message);
    }
  }, [message]);

  return (
    <div className="log-in-page">
      <div className="banner-container">
        <img className="banner-image" src={Banner} alt="" />
      </div>
      {successMessage && (
        <div className="login-info-message">
          <SuccessInfoMessage
            message={successMessage}
            onClick={handleCloseInfoMessage}
          />
        </div>
      )}
      <div className="form-container">
        <LogInForm />
      </div>
      {loading && <Loader />}
    </div>
  );
};

export default LogInPage;
