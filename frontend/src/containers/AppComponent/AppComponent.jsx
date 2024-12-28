import CarBookPage from '@pages/CarBookPage/CarBookPage';
import './App.css';
import store from '@/redux/store';
import { Provider } from 'react-redux';
function App() {
  return (
    <div className="App">
      <Provider store={store}>
        <CarBookPage />
      </Provider>
    </div>
  );
}

export default App;
