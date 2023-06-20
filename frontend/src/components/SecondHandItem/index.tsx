import Icon from '../Icon';
import elapsedTime from '../../utils/elapsedTime';
import formatNumber from '../../utils/formatNumber';
import * as S from './styles';
import IconWithCount from './IconWithCount';

interface SecondHandItemProps {
  title: string;
  createdAt: string;
  status: '판매중' | '예약중' | '판매완료';
  price: number | null;
  location: string;
  chatCount: number;
  interestCount: number;
  imageURI: string;
  option?: boolean;
}

const SecondHandItem = ({
  title,
  createdAt,
  status,
  price,
  location,
  chatCount,
  interestCount,
  imageURI,
  option = false,
}: SecondHandItemProps) => {
  return (
    <S.ItemContainer>
      <S.ItemImage imageURI={imageURI} />
      <S.ItemInfo>
        <S.ItemContents>
          <S.Content>
            <S.ColumnTop>{title}</S.ColumnTop>
            <S.ColumnMid>
              {location} ・ {elapsedTime(createdAt)}
            </S.ColumnMid>
            <S.ColumnBot>
              {status !== '판매중' && (
                <S.StatusLabel round={true} status={status}>
                  {status}
                </S.StatusLabel>
              )}
              {price !== null && <S.Price>{formatNumber(price)}원</S.Price>}
            </S.ColumnBot>
          </S.Content>
          <div className="Option">
            {option && <Icon name={'ellipsis'} width={'17'} height={'20'} />}
          </div>
        </S.ItemContents>
        <S.ItemIssue>
          <IconWithCount name={'message'} count={chatCount} />
          <S.HeartIconWithCount>
            <IconWithCount name={'heart'} count={interestCount} />
          </S.HeartIconWithCount>
        </S.ItemIssue>
      </S.ItemInfo>
    </S.ItemContainer>
  );
};

export default SecondHandItem;
