import { Provider } from 'react-redux';
import { MemoryRouter } from 'react-router-dom';
import store from '@/redux/store';
import AppComponent from './AppComponent';

export default {
  title: 'Components/AppComponent',
  component: AppComponent,
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

const Template = (args) => <AppComponent {...args} />;

export const Default = Template.bind({});
Default.args = {};
