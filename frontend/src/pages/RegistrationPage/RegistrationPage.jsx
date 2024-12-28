import Banner from '@assets/Banner.png';
import './RegistrationPage.css';
import RegistrationForm from './components/RegistrationForm';
import { useSelector } from 'react-redux';
import Loader from '@/components/atoms/Loader/Loader';

const RegistrationPage = () => {
  const { loading } = useSelector((state) => state.auth);

  return (
    <div className="registration">
      <div className="registration__banner-container">
        <img
          className="registration__banner-image"
          src={Banner}
          alt="banner-image"
        />
      </div>
      <div className="registration__form-container">
        <RegistrationForm />
      </div>
      {loading && <Loader />}
    </div>
  );
};

export default RegistrationPage;
