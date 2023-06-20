import { Outlet } from 'react-router-dom';

import TabBar from '../TabBar';

const Layout = () => {
  return (
    <>
      <Outlet />
      <TabBar />
    </>
  );
};

export default Layout;
