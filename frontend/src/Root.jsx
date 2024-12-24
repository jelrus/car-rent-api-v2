import { Provider } from 'react-redux';
import store from "@/redux/store";
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import HomePage from '@pages/HomePage/HomePage';
import LogInPage from '@pages/LogInPage/LogInPage';
import RegistrationPage from '@pages/RegistrationPage/RegistrationPage';
import App from './App.jsx'
import CarsPage from '@pages/CarsPage/CarsPage';
import BookingsPage from './pages/BookingsPage/BookingsPage.jsx';

export const Root = () => (
  <BrowserRouter>
    <Provider store={store}>
      <Routes>
        <Route path="/" element={<App />}>
          <Route index element={<HomePage />} />
          <Route path="/home" element={<HomePage />} />
          <Route path="/cars" element={<CarsPage />} />  
          <Route path="/bookings">
            <Route index element={<BookingsPage />} />
            <Route path=":tabId?" element={<BookingsPage />} />
          </Route>
        </Route> 
        <Route path="/login" element={<LogInPage />} />
        <Route path="/signup" element={<RegistrationPage />} />
        <Route path="*" element={<p>Not Found</p>} />
      </Routes>
    </Provider>
  </BrowserRouter>
)

