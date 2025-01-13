import PropTypes from 'prop-types';
import styles from './MessageCard.module.css';
import Button from '../Button/Button';

const ModalMessageCard = ({ header, message, onClose }) => {
  const handleOverlayClick = (e) => {
    if (e.target === e.currentTarget) {
      onClose();
    }
  };

  return (
    <div className={styles.modalOverlay} onClick={handleOverlayClick}>
      <div className={styles.modalMessageCard}>
        {header && (
          <div className={styles.modalMessageCardHeader}>{header}</div>
        )}
        <div className={styles.modalMessageCardContent}>
          <div className={styles.modalMessageCardMessage}>{message}</div>
          <Button text="Close" ButtonType="primary" onClick={onClose} />
        </div>
      </div>
    </div>
  );
};

ModalMessageCard.propTypes = {
  header: PropTypes.string,
  message: PropTypes.node.isRequired,
  onClose: PropTypes.func.isRequired,
};

export default ModalMessageCard;
