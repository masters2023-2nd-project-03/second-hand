import { createBrowserRouter, RouterProvider } from 'react-router-dom';

import { ThemeProvider } from 'styled-components';

import theme from '../../styles/theme';
import GlobalStyles from '../../styles/GlobalStyles';
import HomePage from '../HomePage';
import SalesPage from '../SalesPage';
import HeartPage from '../HeartPage';
import ChatPage from '../ChatPage';
import ErrorPage from '../ErrorPage';
import Login from '../../pages/Login';
import CatalogPage from '../CatalogPage';
import Layout from '../Layout';
import Account from '../../pages/Account';
import {
  ACCOUNT,
  CALL_BACK,
  CATALOG,
  CHAT,
  HEART,
  HOME,
  LOGIN,
  SALES,
} from '../../constants/routeUrl';
import { AuthProvider } from '../../context/Auth';
import ProtectedRoute from '../ProtectedRoute';
import Callback from '../Callback/';

const router = createBrowserRouter([
  {
    path: '/',
    element: <Layout />,
    errorElement: <ErrorPage />,
    children: [
      {
        path: HOME,
        element: (
          // <ProtectedRoute>
          <HomePage />
          // </ProtectedRoute>
        ),
      },
      {
        path: SALES,
        element: (
          // <ProtectedRoute>
          <SalesPage />
          // </ProtectedRoute>
        ),
      },
      {
        path: HEART,
        element: (
          // <ProtectedRoute>
          <HeartPage />
          // </ProtectedRoute>
        ),
      },
      {
        path: CHAT,
        element: (
          // <ProtectedRoute>
          <ChatPage />
          // </ProtectedRoute>
        ),
      },
      {
        path: ACCOUNT,
        element: (
          <ProtectedRoute>
            <Account />
          </ProtectedRoute>
        ),
      },
      { path: CATALOG, element: <CatalogPage /> },
      { path: LOGIN, element: <Login /> },
      { path: CALL_BACK, element: <Callback /> },
    ],
  },
]);

const App = () => {
  return (
    <AuthProvider>
      <ThemeProvider theme={theme}>
        <GlobalStyles />
        <RouterProvider router={router} />
      </ThemeProvider>
    </AuthProvider>
  );
};

export default App;
