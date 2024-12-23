import InputField from '../InputField';

export default {
  title: 'Atoms/InputField',
  component: InputField,
};

const Template = (args) => <InputField {...args} />;

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
