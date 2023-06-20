import { useNavigate } from 'react-router-dom';

import { useAuthContext } from '../../context/Auth';
import useAsync from '../../hooks/useAsync';
import { LOGIN } from '../../constants/routeUrl';
import { AUTHORIZATION_CODE } from '../../constants/login';
import { postLogin } from '../../api/login';

const Callback = () => {
  const navigate = useNavigate();
  const { handleLogin } = useAuthContext();
  // TODO (시저) : as 없애는 방법 찾아보기
  const { data, isLoading, error, refetch } = useAsync(
    () => postLogin(code as string),
    [],
    true,
  );
  // 1. code값을 읽어온다.
  const searchParams = new URLSearchParams(window.location.search);
  const code = searchParams.get(AUTHORIZATION_CODE);

  // TODO (시저) : code 없을때 어디로? 일단 임시로 다시 로그인 페이지
  if (!code) navigate(LOGIN);

  // 2. 백엔드에게 code를 보낸다.
  refetch();

  // TODO (시저) : 여기도 일단 로그인 페이지
  if (error) navigate(LOGIN);
  // TODO (시저) : suspense랑 연동할 수 있는지 나중에 공부하기
  if (isLoading) return <div>로딩중</div>;

  // TODO (시저) : useAsync에서 status code도 반환하기
  // 3. (상태 코드) 기존 회원의 경우 홈화면으로 이동
  // 수도코드
  // if(status code === 2xx) {
  //  handleLogin 호출
  //  navigate (상품 목록 화면)
  // }

  // 4. (상태 코드) 최초 로그인의 경우 회원가입 페이지로 이동
  // 수도코드
  // if(status code === 2xx) {
  //  navigate (회원가입 화면)
  // }

  return <div>Callback</div>;
};

export default Callback;
