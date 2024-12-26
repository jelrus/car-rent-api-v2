import './Header.css';
import Notification from '@assets/Notification.svg';
import { useSelector, useDispatch } from 'react-redux';
import { NavLink, Link, useNavigate } from 'react-router-dom';
import classNames from 'classnames';
import { logOut } from '@/redux/slices/authSlice';

const Header = () => {
  const { username, userImageUrl, role } = useSelector((state) => state.auth.user);
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const logOutHandler = () => {
    dispatch(logOut());
    navigate('/');
  }
  return (
    <div className='headerContainer'>
      <div className='logo'>
        <span className='flexi'>Flexi</span>
        <span className='ride'>Ride</span>
      </div>
      <nav className='nav'>
        <NavLink 
          className={
            ({ isActive }) =>
              classNames('nav-link', { active: isActive })
            } 
          to="/home">
            Home
        </NavLink>
        <NavLink className={
                ({ isActive }) =>
                  classNames('nav-link', { active: isActive })
                } to="/cars">
                Cars
        </NavLink>
        {role && (
          <NavLink className={
            ({ isActive }) =>
              classNames('nav-link', { active: isActive })
            } to="/bookings">My Bookings</NavLink>
        )}
      </nav>
      {role && (
        <div className='auth-header'>
          <div>
            <img className='userlogo' src={userImageUrl} />
          </div>
          <div className='client-information'>
            <span>
              Hello, {username} ({role})
            </span>
          </div>
          <div>
            <img className='notification' src={Notification} />
          </div>
        </div>
      )}
      <div className='auth-container'>
        <span>
          {role ? (
            <button className='hyperlink-button' onClick={logOutHandler} >Log out</button>
          ) : (
            <Link className='hyperlink' to='/login'>
                Log In
            </Link>
          )}
          <select className='language-dropdown'>
              <option value="en">En</option>
          </select>  
        </span>
      </div>
    </div>
  );
};
export default Header;
