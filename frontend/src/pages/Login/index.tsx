import { GITHUB_CLIENT_ID, GITHUB_OAUTH_URL } from '../../constants/login';
import Button from '../../components/Button';
import * as S from './styles';
import NavBarTitle from '../../components/NavBarTitle';

const Login = () => {
  const onLoginButtonClick = () => {
    window.location.assign(`${GITHUB_OAUTH_URL}?client_id=${GITHUB_CLIENT_ID}`);
  };

  return (
    <>
      <NavBarTitle type="high" centerTitle="내 계정" />
      <S.Container>
        <Button fullWidth active onClick={onLoginButtonClick}>
          로그인
        </Button>
      </S.Container>
    </>
  );
};

export default Login;
