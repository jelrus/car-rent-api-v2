import ButtonLink from '@/components/atoms/ButtonLink/ButtonLink';
import './PersonalCard.css';
import PropTypes from 'prop-types';

const PersonalCard = ({ user }) => {
  const onChange = () => {
    alert('change');
  };
  return (
    <div className="personal-card">
      <div className="personal-info">
        <span>{user.name}</span>
        <span>{user.email}</span>
        <span>{user.phone}</span>
      </div>
      <div>
        <ButtonLink text="Change" type="primary" onClick={onChange} />
      </div>
    </div>
  );
};

PersonalCard.propTypes = {
  user: PropTypes.shape({
    name: PropTypes.string.isRequired,
    email: PropTypes.string.isRequired,
    phone: PropTypes.string.isRequired,
  }).isRequired,
};

export default PersonalCard;
