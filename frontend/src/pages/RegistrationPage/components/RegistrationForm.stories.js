
import { Provider } from 'react-redux';
import { MemoryRouter } from 'react-router-dom';
import { configureStore } from '@reduxjs/toolkit';
import registerReducer from '@redux/reducers/registerReducer';
import RegistrationForm from './RegistrationForm';

const mockStore = configureStore({
  reducer: {
    register: registerReducer,
  },
  preloadedState: {
    register: {
      isAuth: false,
      loading: false,
    },
  },
});

export default {
  title: 'Components/RegistrationForm',
  component: RegistrationForm,
  decorators: [
    (Story) => (
      <Provider store={mockStore}>
        <MemoryRouter>
          <Story />
        </MemoryRouter>
      </Provider>
    ),
  ],
};

const Template = (args) => <RegistrationForm {...args} />;

export const Default = Template.bind({});
Default.args = {};

export const WithError = Template.bind({});
WithError.decorators = [
  (Story) => (
    <Provider
      store={configureStore({
        reducer: {
          register: registerReducer,
        },
        preloadedState: {
          register: {
            isAuth: false,
            loading: false,
          },
        },
      })}
    >
      <MemoryRouter>
        <Story />
      </MemoryRouter>
    </Provider>
  ),
];
WithError.play = async ({ canvasElement }) => {
  const emailInput = canvasElement.querySelector('#email');
  const passwordInput = canvasElement.querySelector('#password');
  const registerButton = canvasElement.querySelector('button[type="primary"]');

  emailInput.value = 'invalid-email';
  passwordInput.value = '123';
  registerButton.click();
};

export const LoadingState = Template.bind({});
LoadingState.decorators = [
  (Story) => (
    <Provider
      store={configureStore({
        reducer: {
          register: registerReducer,
        },
        preloadedState: {
          register: {
            isAuth: false,
            loading: true,
          },
        },
      })}
    >
      <MemoryRouter>
        <Story />
      </MemoryRouter>
    </Provider>
  ),
];
