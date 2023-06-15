import Icon from '../Icon';
import elapsedTime from '../../utils/elapsedTime';
import formatNumber from '../../utils/formatNumber';
import * as S from './styles';

interface SecondHandItemProps {
  title: string;
  createdAt: string;
  status: string;
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
          {chatCount > 0 && (
            <S.Message>
              <Icon name={'message'} width={'24'} height={'20'} />
              <span>{chatCount}</span>
            </S.Message>
          )}
          {interestCount > 0 && (
            <S.heart>
              <Icon name={'heart'} width={'24'} height={'20'} />
              <span>{interestCount}</span>
            </S.heart>
          )}
        </S.ItemIssue>
      </S.ItemInfo>
    </S.ItemContainer>
  );
};

export default SecondHandItem;
