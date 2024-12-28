/** @type { import('@storybook/react').Preview } */
import { Provider } from 'react-redux';
import { store } from '/src/redux/store.js';
import { BrowserRouter } from 'react-router';

const preview = {
  parameters: {
    controls: {
      matchers: {
        color: /(background|color)$/i,
        date: /Date$/i,
      },
    },
  },
  decorators: [
    (Story) => (
      <BrowserRouter>
        <Provider store={store}>
          <Story />
        </Provider>
      </BrowserRouter>
    ),
  ],
};

export default preview;
