import { useState } from 'react';
import PasswordField from '../components/PasswordField';

export default {
  title: 'Components/PasswordField',  
  component: PasswordField,
};

const Template = (args) => {
  const [value, setValue] = useState('');
  return <PasswordField {...args} value={value} onChange={(e) => setValue(e.target.value)} />;
};

export const Default = Template.bind({});
Default.args = {
  id: 'password',
  placeholder: 'Enter your password',
  label: 'Password',
  underMessage: 'Password must be at least 8 characters long.',
  typeUnderMessage: 'info',
};

export const WithError = Template.bind({});
WithError.args = {
  id: 'password',
  placeholder: 'Enter your password',
  label: 'Password',
  underMessage: 'Password is too short.',
  typeUnderMessage: 'error',
};

export const ShowPassword = Template.bind({});
ShowPassword.args = {
  id: 'password',
  placeholder: 'Enter your password',
  label: 'Password',
  value: 'password123',
  onChange: () => {},
  underMessage: 'Password is strong.',
  typeUnderMessage: 'info',
};
