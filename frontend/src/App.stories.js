
import { Provider } from 'react-redux';
import { MemoryRouter } from 'react-router-dom';
import store from '@/redux/store';
import App from './App';

export default {
  title: 'Components/App',
  component: App,
  decorators: [
    (Story) => (
      <Provider store={store}>
        <MemoryRouter>
          <Story />
        </MemoryRouter>
      </Provider>
    ),
  ],
};

const Template = (args) => <App {...args} />;

export const Default = Template.bind({});
Default.args = {};
