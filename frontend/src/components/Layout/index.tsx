import { Outlet } from 'react-router-dom';

import * as S from './styles';

import TabBar from '../TabBar';

const Layout = () => {
  return (
    <>
      <S.Main>
        <Outlet />
      </S.Main>
      <TabBar />
    </>
  );
};

export default Layout;
