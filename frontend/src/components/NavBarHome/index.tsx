import Dropdown from '../Dropdown';
import Icon from '../Icon';
import * as S from './styles';

interface NavBarHomeProps {
  type: string;
}

const NavBarHome = ({ type }: NavBarHomeProps) => {
  return (
    <S.navBarContainer type={type}>
      <S.navBarBody>
        <Dropdown options={['역삼1동', '역삼2동']} />
        <Icon name={'label'} />
      </S.navBarBody>
    </S.navBarContainer>
  );
};

export default NavBarHome;
