import { Provider } from 'react-redux';
import { MemoryRouter } from 'react-router-dom';
import { configureStore } from '@reduxjs/toolkit';
import rootReducer from '@/redux/rootReducer';
import RegistrationPage from './RegistrationPage';

const mockStore = configureStore({
  reducer: rootReducer,
  preloadedState: {
    register: {
      isAuth: false,
      loading: false,
    },
  },
});

export default {
  title: 'Pages/RegistrationPage',
  component: RegistrationPage,
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

const Template = (args) => <RegistrationPage {...args} />;

export const Default = Template.bind({});
Default.args = {};

export const AuthenticatedRedirect = Template.bind({});
AuthenticatedRedirect.decorators = [
  (Story) => (
    <Provider
      store={configureStore({
        reducer: rootReducer,
        preloadedState: {
          register: {
            isAuth: true,
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
