import Dropdown from '../Dropdown';
import Icon from '../Icon';
import * as S from './styles';

interface NavBarHomeProps {
  type: string;
  iconOnClick?: () => void;
}

const NavBarHome = ({ type, iconOnClick }: NavBarHomeProps) => {
  return (
    <S.NavBarContainer type={type}>
      <S.NavBarBody>
        <S.ClinkElement>
          <Dropdown options={['역삼1동', '역삼2동']} />
        </S.ClinkElement>
        <S.ClinkElement>
          <Icon onClick={iconOnClick} name={'label'} />
        </S.ClinkElement>
      </S.NavBarBody>
    </S.NavBarContainer>
  );
};

export default NavBarHome;
