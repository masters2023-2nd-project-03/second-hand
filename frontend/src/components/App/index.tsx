import { createBrowserRouter, RouterProvider } from 'react-router-dom';

import { ThemeProvider } from 'styled-components';

import theme from '../../styles/theme';
import GlobalStyles from '../../styles/GlobalStyles';
import HomePage from '../../pages/Home';
import SalesPage from '../../pages/Sales';
import HeartPage from '../../pages/Heart';
import ChatPage from '../../pages/Chat';
import ErrorPage from '../../pages/Error';
import Login from '../../pages/Login';
import CategoryPage from '../../pages/Category';
import Layout from '../Layout';
import Account from '../../pages/Account';
import SalesMyItemPage from '../../pages/SalesMyItem';
import ChatRoom from '../../pages/ChatRoom';
import { LocationPage } from '../../pages/Location';
import { LocationSetPage } from '../../pages/LocationSet';
import {
  ACCOUNT,
  CALL_BACK,
  CHAT,
  HEART,
  HOME,
  LOGIN,
  SALES,
  CATEGORY,
  SALES_ITEM,
  REGISTER,
  ITEM_DETAIL,
  CHATROOM,
  LOCATION,
  LOCATION_SET,
} from '../../constants/routeUrl';
import { AuthProvider } from '../../context/Auth';
import ProtectedRoute from '../ProtectedRoute';
import Callback from '../Callback/';
import Register from '../../pages/Register';
import ItemDetail from '../../pages/ItemDetail';

const router = createBrowserRouter([
  {
    path: '/',
    element: <Layout />,
    errorElement: <ErrorPage />,
    children: [
      {
        path: `${HOME}/:categoryId?`,
        element: <HomePage />,
      },
      {
        path: SALES,
        element: (
          <ProtectedRoute>
            <SalesPage />
          </ProtectedRoute>
        ),
      },
      {
        path: HEART,
        element: (
          <ProtectedRoute>
            <HeartPage />
          </ProtectedRoute>
        ),
      },
      {
        path: CHAT,
        element: (
          <ProtectedRoute>
            <ChatPage />
          </ProtectedRoute>
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
      { path: LOGIN, element: <Login /> },
      { path: CALL_BACK, element: <Callback /> },
    ],
  },

  {
    path: CATEGORY,
    element: <CategoryPage />,
  },
  {
    path: SALES_ITEM,
    element: <SalesMyItemPage />,
  },
  { path: REGISTER, element: <Register /> },
  {
    path: `${ITEM_DETAIL}/:productsId`,
    element: (
      <ProtectedRoute>
        <ItemDetail />
      </ProtectedRoute>
    ),
  },
  { path: `${CHATROOM}/:roomId`, element: <ChatRoom /> },
  { path: LOCATION, element: <LocationPage /> },
  { path: LOCATION_SET, element: <LocationSetPage /> },
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
