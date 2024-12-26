import { Outlet, Link } from 'react-router-dom';
import './App.css'

const App = () =>  {
  return (
    <div className='App'>
      <header>
        <nav>
          <ul>
            <li><Link to="/home">Home</Link></li>
            <li><Link to="/cars">Cars</Link></li>
            <li><Link to="/login">Log In</Link></li>
            <li><Link to="/signup">Register</Link></li>
            <li><Link to="/bookings">Bookings</Link></li>
          </ul>
        </nav>
      </header>
      <main className='main'>
        <Outlet />
      </main>
      <footer>
        <p>Car Rent</p>
      </footer>
    </div>
  );
}

export default App;
