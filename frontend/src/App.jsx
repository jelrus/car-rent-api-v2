import { Outlet } from 'react-router';
import './App.css'

function App() {
  return (
    <div>
      <header>
        <nav>
          <ul>
            <li><a href="/home">Home</a></li>
            <li><a href="/cars">Cars</a></li>
            <li><a href="/login">Log In</a></li>
            <li><a href="/signup">Register</a></li>
          </ul>
        </nav>
      </header>
      <main>
        <Outlet />
      </main>
      <footer>
        <p>Car Rent</p>
      </footer>
    </div>
  );
}

export default App;
