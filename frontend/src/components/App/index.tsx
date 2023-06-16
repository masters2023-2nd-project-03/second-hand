import { createBrowserRouter, RouterProvider } from 'react-router-dom';

import { ThemeProvider } from 'styled-components';

import theme from '../../styles/theme';
import GlobalStyles from '../../styles/GlobalStyles';
import HomePage from '../HomePage';
import SalesPage from '../SalesPage';
import HeartPage from '../HeartPage';
import ChatPage from '../ChatPage';
import AccountPage from '../AccountPage';

const App = () => {
  const router = createBrowserRouter([
    {
      path: '/',
      element: <HomePage />,
      // TODO(sarang_daddy) : 에러 페이지 만들기
      // errorElement: <ErrorPage />,
    },
    {
      path: '/sales',
      element: <SalesPage />,
    },
    {
      path: '/heart',
      element: <HeartPage />,
    },
    {
      path: '/chat',
      element: <ChatPage />,
    },
    {
      path: '/account',
      element: <AccountPage />,
    },
  ]);

  return (
    <>
      <ThemeProvider theme={theme}>
        <GlobalStyles />
        <RouterProvider router={router} />
      </ThemeProvider>
    </>
  );
};

export default App;
