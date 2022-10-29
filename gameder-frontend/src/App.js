import logo from './logo.svg';
import Home from './pages/Home';
import Dashboard from './pages/Dashboard';
import Onboarding from './pages/Onboarding';
import {BrowserRouter, Routes, Route } from 'react-router-dom'
import { useCookies } from 'react-cookie'


const App = () => {

  const [cookies, setCookie, removeCookie] = useCookies()
  const jwToken = cookies.jwToken

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        {jwToken && <Route path="/dashboard" element={<Dashboard />} />}
        {jwToken && <Route path="/onboarding" element={<Onboarding />} />}
      </Routes>
    </BrowserRouter>
  );
}

export default App;
