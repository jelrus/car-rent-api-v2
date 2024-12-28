import { Provider } from 'react-redux';
import store from '@/redux/store';
import { HashRouter, Route, Routes, Navigate } from 'react-router';
import HomePage from '@pages/HomePage/HomePage';
import LogInPage from '@pages/LogInPage/LogInPage';
import RegistrationPage from '@pages/RegistrationPage/RegistrationPage';
import App from './App.jsx';
import CarsPage from '@pages/CarsPage/CarsPage';
import BookingsPage from './pages/BookingsPage/BookingsPage.jsx';
import CarBookPage from '@pages/CarBookPage/CarBookPage.jsx';

export const Root = () => (
  <HashRouter>
    <Provider store={store}>
      <Routes>
        <Route path="/" element={<App />}>
          <Route index element={<Navigate to="/home" replace />} />
          <Route path="/home" element={<HomePage />} />
          <Route path="/cars" element={<CarsPage />} />
          <Route path="/bookings">
            <Route index element={<BookingsPage />} />
            <Route path=":tabId?" element={<BookingsPage />} />
            <Route path=":paramCarId" element={<CarBookPage />} />
          </Route>
        </Route>
        <Route path="/login" element={<LogInPage />} />
        <Route path="/signup" element={<RegistrationPage />} />
        <Route path="/booking/:paramCarId" element={<CarBookPage />} />
        <Route path="*" element={<p>Not Found</p>} />
      </Routes>
    </Provider>
  </HashRouter>
);
