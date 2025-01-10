import { NavLink } from 'react-router-dom';
import { useState } from 'react';
import PropTypes from 'prop-types';
import './ProfileNavigation.css';

const ProfileNavigation = ({ onNavClick }) => {
  return (
    <nav className="profile__nav">
      <ul className="profile__nav-list">
        <li className="profile__nav-item">
          <a
            href="#"
            className="profile__nav-link"
            onClick={() => onNavClick('reviews')}
          >
            Reviews
          </a>
        </li>
        <li className="profile__nav-item">
          <a
            href="#"
            className="profile__nav-link"
            onClick={() => onNavClick('personal-info')}
          >
            Personal info
          </a>
        </li>
        <li className="profile__nav-item">
          <a
            href="#"
            className="profile__nav-link"
            onClick={() => onNavClick('documents')}
          >
            Documents
          </a>
        </li>
        <li className="profile__nav-item">
          <a
            href="#"
            className="profile__nav-link"
            onClick={() => onNavClick('change-password')}
          >
            Change password
          </a>
        </li>
        <li className="profile__nav-item">
          <a href="#" className="profile__nav-link">
            Log out
          </a>
        </li>
      </ul>
    </nav>
  );
};
ProfileNavigation.propTypes = {
  onNavClick: PropTypes.func.isRequired,
};

export default ProfileNavigation;
