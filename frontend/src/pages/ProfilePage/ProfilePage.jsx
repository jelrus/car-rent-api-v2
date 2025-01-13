import ProfileNavigation from './components/ProfileNavigation/ProfileNavigation';
import PersonalInfo from './components/PersonalInfo/PersonalInfo';
import { useState } from 'react';
import './ProfilePage.css';

const ProfilePage = () => {
  const [activeSection, setActiveSection] = useState('personal-info');

  const handleNavClick = (section) => {
    setActiveSection(section);
  };

  return (
    <div className="profile__page">
      <div className="profile__title">
        <h2>My profile</h2>
      </div>
      <div className="profile__content">
        <ProfileNavigation onNavClick={handleNavClick} />
        {activeSection === 'personal-info' && <PersonalInfo />}
        {activeSection === 'reviews' && <Reviews />}
        {activeSection === 'documents' && <Documents />}
        {activeSection === 'change-password' && <ChangePassword />}
      </div>
    </div>
  );
};

export default ProfilePage;
