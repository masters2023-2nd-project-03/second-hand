import { Outlet } from 'react-router-dom';

import TabBar from '../TabBar';
import * as S from './styles';

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
