import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

import { useAuthContext } from '../../context/Auth';
import useAsync from '../../hooks/useAsync';
import { AUTHORIZATION_CODE } from '../../constants/login';
import { postLogin } from '../../api/login';
import { HOME, REGISTER } from '../../constants/routeUrl';

const Callback = () => {
  const searchParams = new URLSearchParams(window.location.search);
  const code = searchParams.get(AUTHORIZATION_CODE);
  const { data } = useAsync(() => postLogin(code));
  const { handleLogin } = useAuthContext();
  const navigate = useNavigate();

  useEffect(() => {
    // TODO : 에러메세지 협의 끝나면 상수로 분리
    if (data?.status === 'FORBIDDEN') {
      const { nickname, profileUrl, oauthId } = data.data;
      navigate(
        `${REGISTER}?nickname=${nickname}&profileUrl=${profileUrl}&oauthId=${oauthId}`,
      );
    }

    if (data?.status === 'OK') {
      const { jwt } = data.data;
      handleLogin(jwt);
      navigate(HOME);
    }
  }, [data]);

  return <div>Callback</div>;
};

export default Callback;
