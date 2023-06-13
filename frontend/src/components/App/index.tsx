import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import { ThemeProvider } from 'styled-components';

import theme from '../../styles/theme';
import GlobalStyles from '../../styles/GlobalStyles';
import HomePage from '../HomePage';
import SalesPage from '../SalesPage';
import HeartPage from '../HeartPage';
import ChatPage from '../ChatPage';
import AccountPage from '../AccountPage';
import ErrorPage from '../ErrorPage';
import TabBar from '../TabBar';

const App = () => {
  return (
    <ThemeProvider theme={theme}>
      <GlobalStyles />
      <Router>
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/sales" element={<SalesPage />} />
          <Route path="/heart" element={<HeartPage />} />
          <Route path="/chat" element={<ChatPage />} />
          <Route path="/account" element={<AccountPage />} />
          <Route path="*" element={<ErrorPage />} />
        </Routes>
      </Router>
      <TabBar />
    </ThemeProvider>
  );
};

export default App;
