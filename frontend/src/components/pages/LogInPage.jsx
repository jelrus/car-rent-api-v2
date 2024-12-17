import './LogInPage.css';
import LogInForm from '../organisms/LogInForm';
import Banner from '../../assets/Banner.png'

const LogInPage = () => {
	return (
		<div className='log-in-page'>
      <div className='banner-container'>
        <img className='banner-image' src={Banner} alt="" />
			</div>
      <div className='form-container'>
        <LogInForm />
			</div>
		</div>
	);
};

export default LogInPage;
