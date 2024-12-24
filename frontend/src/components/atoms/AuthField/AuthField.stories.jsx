import AuthField from './AuthField';

export default {
  title: 'Atoms/AuthField',
  component: AuthField,
};

const Template = (args) => <AuthField {...args} />;

export const EmailInput = Template.bind({});
EmailInput.args = {
  id: 'email',
  type: 'email',
  placeholder: 'Write your email',
};

export const PasswordInput = Template.bind({});
PasswordInput.args = {
  id: 'password',
  type: 'password',
  placeholder: 'Write your password',
};
