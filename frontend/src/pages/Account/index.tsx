import Button from '../../components/Button';
import { useAuthContext } from '../../context/Auth';

const Account = () => {
  const { handleLogout } = useAuthContext();

  const onClick = () => {
    handleLogout();
  };

  return (
    <div>
      <Button fullWidth active onClick={onClick}>
        로그아웃
      </Button>
    </div>
  );
};

export default Account;
