import Banner from '@assets/Banner.png';
import './RegistrationPage.css';
import RegistrationForm from './components/RegistrationForm';

const RegistrationPage = () => {
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
    </div>
  );
};

export default RegistrationPage;
