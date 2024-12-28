import Banner from '@assets/Banner.png';
import './LogInPage.css';
import LogInForm from './components/LogInForm/LogInForm';

const LogInPage = () => {
  return (
    <div className="log-in-page">
      <div className="banner-container">
        <img className="banner-image" src={Banner} alt="" />
      </div>
      <div className="form-container">
        <LogInForm />
      </div>
    </div>
  );
};

export default LogInPage;
