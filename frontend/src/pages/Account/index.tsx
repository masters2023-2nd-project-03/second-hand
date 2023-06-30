import Button from '../../components/Button';
import { useAuthContext } from '../../context/Auth';
import Icon from '../../components/Icon';
import * as S from './styles';
import NavBarTitle from '../../components/NavBarTitle';

const Account = () => {
  const { handleLogout, userInfo } = useAuthContext();

  const onClick = () => {
    handleLogout();
  };

  return (
    <>
      <NavBarTitle type="high" centerTitle="내 계정" />
      <S.Container>
        <S.ProfileImageButton circle profileImageUrl={userInfo.profileUrl}>
          <Icon name="camera" />
        </S.ProfileImageButton>
        <Button fullWidth active onClick={onClick}>
          로그아웃
        </Button>
      </S.Container>
    </>
  );
};

export default Account;
