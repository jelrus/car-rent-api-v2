import PropTypes from 'prop-types';
import PasswordField from '@components/atoms/PasswordField/PasswordField';
import AuthField from '../atoms/AuthField/AuthField';

const FormField = ({ fieldType, ...props }) => {
  switch (fieldType) {
    case 'input':
      return <AuthField {...props} />;
    case 'password':
      return <PasswordField {...props} />;
    default:
      return null;
  }
};

FormField.propTypes = {
  fieldType: PropTypes.oneOf(['password', 'input']).isRequired,
};

export default FormField;
