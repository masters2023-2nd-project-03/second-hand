import { createBrowserRouter, RouterProvider } from 'react-router-dom';

import { ThemeProvider } from 'styled-components';

import theme from '../../styles/theme';
import GlobalStyles from '../../styles/GlobalStyles';
import HomePage from '../HomePage';
import SalesPage from '../SalesPage';
import HeartPage from '../HeartPage';
import ChatPage from '../ChatPage';
import AccountPage from '../AccountPage';
import ErrorPage from '../ErrorPage';
import Layout from '../Layout';

const router = createBrowserRouter([
  {
    path: '/',
    element: <Layout />,
    errorElement: <ErrorPage />,
    children: [
      { path: '/', element: <HomePage /> },
      { path: '/sales', element: <SalesPage /> },
      { path: '/heart', element: <HeartPage /> },
      { path: '/chat', element: <ChatPage /> },
      { path: '/account', element: <AccountPage /> },
    ],
  },
]);

const App = () => {
  return (
    <ThemeProvider theme={theme}>
      <GlobalStyles />
      <RouterProvider router={router} />
    </ThemeProvider>
  );
};

export default App;
