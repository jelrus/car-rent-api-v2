import Banner from '@assets/Banner.png';
import './LogInPage.css';
import LogInForm from './components/LogInForm/LogInForm';
import Loader from '@/components/atoms/Loader/Loader';
import { useSelector } from 'react-redux';

const LogInPage = () => {
  const { loading } = useSelector((state) => state.auth);

  return (
    <div className="log-in-page">
      <div className="banner-container">
        <img className="banner-image" src={Banner} alt="" />
      </div>
      <div className="form-container">
        <LogInForm />
      </div>
      {loading && <Loader />}
    </div>
  );
};

export default LogInPage;
