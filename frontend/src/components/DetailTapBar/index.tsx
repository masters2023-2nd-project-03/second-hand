import formatNumber from '../../utils/formatNumber';
import Button from '../Button';
import Icon from '../Icon';
import * as S from './styles';

interface DetailTapBarProps {
  price: number | null;
}

const DetailTapBar = ({ price }: DetailTapBarProps) => {
  return (
    <>
      <S.DetailTapBarContainer>
        <S.Menu>
          <div>
            <S.Left>
              <Icon name="heart" width="27" height="28" />
              {price !== null && <S.Price>{formatNumber(price)}원</S.Price>}
            </S.Left>
          </div>
          <S.Right>
            <Button active>대화 중인 채팅방</Button>
          </S.Right>
        </S.Menu>
      </S.DetailTapBarContainer>
    </>
  );
};

export default DetailTapBar;
