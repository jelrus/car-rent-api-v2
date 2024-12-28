import PropTypes from "prop-types";
import './MessageCard.css'; 
import Button from "../Button/Button";

const ModalMessageCard = ({ header, message, onClose }) => {
  const handleOverlayClick = (e) => {
    if (e.target === e.currentTarget) {
      onClose();
    }
  };

  return (
    <div className="modal-overlay" onClick={handleOverlayClick}>
      <div className="modal-message-card">
        <div className="modal-message-card__header">{header}</div>
        <div className="modal-message-card__content">
          <div className="modal-message-card__message">{message}</div>
          <Button text="Close" type="primary" onClick={onClose} />
        </div>
      </div>
    </div>
  );
};

ModalMessageCard.propTypes = {
  header: PropTypes.string.isRequired,
  message: PropTypes.node.isRequired,
  onClose: PropTypes.func.isRequired,
};

export default ModalMessageCard;
