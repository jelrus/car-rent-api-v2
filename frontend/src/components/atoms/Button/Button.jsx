import PropTypes from 'prop-types';
import './Button.css';

const Button = ({ text, onClick, type }) => {
    return (
        <button className={`custom-button custom-button--${type}`}   onClick={onClick}>
            {text}
        </button>
    );
};

Button.propTypes = {
    text: PropTypes.string.isRequired,
    onClick: PropTypes.func,
    type: PropTypes.oneOf(['primary', 'secondary'])
};

export default Button;