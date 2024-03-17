import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import Customer from './pages/Customer';
import Company from './pages/Company';

function App() {
  return (
    <Router>
        <div className="container mt-5">
            {/* Yönlendirmeler */}
            <Routes>
                <Route path="/" element={<Navigate to="/customers" replace />} />
                <Route path="/customers" element={<Customer />} />
                <Route path="/companies" element={<Company />} />
            </Routes>
        </div>
    </Router>
  );
}

export default App;
