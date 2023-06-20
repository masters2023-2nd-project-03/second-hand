import {
  GITHUB_CLIENT_ID,
  GITHUB_OAUTH_URL,
  GITHUB_REDIRECT_URI,
} from '../../constants/login';
import Button from '../../components/Button';

const Login = () => {
  const onLoginButtonClick = () => {
    window.location.assign(
      `${GITHUB_OAUTH_URL}?client_id=${GITHUB_CLIENT_ID}&redirect_uri=${GITHUB_REDIRECT_URI}`,
    );
  };

  return (
    <Button fullWidth active onClick={onLoginButtonClick}>
      로그인
    </Button>
  );
};

export default Login;
