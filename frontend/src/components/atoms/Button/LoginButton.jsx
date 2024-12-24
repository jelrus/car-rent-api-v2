import PropTypes from 'prop-types';
import './Button.css';

const Button = ({ text, type }) => {
    return (
        <button className={`custom-button custom-button--${type}`} >
            {text}
        </button>
    );
};

Button.propTypes = {
    text: PropTypes.string.isRequired,
    type: PropTypes.string.isRequired
};

export default Button;
