import { Outlet } from 'react-router';
import './App.css';
import Header from './components/core/Headers/Header';
import Footer from './components/core/Footers/Footer';

function App() {
  return (
    <div className="App">
      <header>
        <Header />
      </header>
      <main className="main">
        <Outlet />
      </main>
      <footer>
        <Footer />
      </footer>
    </div>
  );
}

export default App;
