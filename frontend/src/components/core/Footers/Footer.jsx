import XIcon from '@assets/XIcon.svg';
import Facebook from '@assets/Facebook.svg';
import Instagram from '@assets/Instagram.svg';
import './Footer.css';
import { NavLink } from 'react-router-dom';
import { useSelector } from 'react-redux';
import classNames from 'classnames';

const Footer = () => {
  const { role } = useSelector((state) => state.auth.user);
  return (
    <div className='footerContainer'>
      <div className='footer-content'>
        <div className='logo'>
          <span>Flexi</span>
          <span className='highlight'>Ride</span>
        </div>
        <nav className='footer-nav'>
        <NavLink className={
                ({ isActive }) =>
                  classNames('footer-nav__link', { footerActive: isActive })
                } to="/cars">
                Cars
        </NavLink>
        {role && (
          <NavLink className={
            ({ isActive }) =>
              classNames('footer-nav__link', { footerActive: isActive })
            } to="/bookings">My Bookings</NavLink>
        )}
        </nav>
        <div className='social-icons'>
          <a className='icon'>
            <img src={Facebook} />

            <a className='icon'>
              <img src={Instagram} />

              <a className='icon'>
                <img src={XIcon} />
              </a>
            </a>
          </a>
        </div>
      </div>
      <div className='copyright'>&copy; 2024 FlexiRide</div>
    </div>
  );
};

export default Footer;
