import Button from './Button';

export default {
    title: 'Atoms/Button',
    component: Button,
};

const Template = (args) => <Button {...args} />;

export const PrimaryButton = Template.bind({});
PrimaryButton.args = {
    text: 'Login',
};