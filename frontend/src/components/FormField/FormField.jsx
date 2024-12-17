import PropTypes from 'prop-types';
import InputField from '@components/atoms/InputField/InputField';
import PasswordField from '@components/atoms/PasswordField/PasswordField';

const FormField = ({ fieldType, ...props }) => {
  switch (fieldType) {
    case 'input':
      return <InputField {...props} />;
    case 'password':
      return <PasswordField {...props} />;
    default:
      return null;
  }
};

FormField.propTypes = {
  fieldType: PropTypes.oneOf(['input']).isRequired,
};

FormField.propTypes = {
  fieldType: PropTypes.oneOf(['password']).isRequired,
};

export default FormField;
