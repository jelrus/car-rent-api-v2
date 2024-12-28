import { useNavigate } from "react-router";
import './UnloginDialog.css';
import  Button  from '@/components/atoms/Button/Button';
import PropTypes from "prop-types";
import  image  from '@/assets/Exclamation.svg';
const UnloginDialog = ({ onClose }) => {
  const navigate = useNavigate();

  const handleOverlayClick = (e) => {
    if (e.target === e.currentTarget) {
      onClose();
    }
  };

  const handleLogin = () => {
    onClose();
    navigate('/login');
  };

  return (
    <div className="unlogin" onClick={handleOverlayClick}>
      <div className="modal-message-card">
				<div className="icon"><img src={`${image}`}/></div>
				<div className="content">
					<div className="text">
        <h4>You are not logged in!</h4>
        <p>To continue booking a car, you need to log in or create an account.</p>
				</div>
        <div className="button-container">
          <Button text="Cancel" type="secondary" onClick={onClose} />
          <Button text="Log in" type="primary" onClick={handleLogin} />
        </div>
				</div>
      </div>
    </div>
  );
};

UnloginDialog.propTypes = {
  onClose: PropTypes.func,
};

export default UnloginDialog;
