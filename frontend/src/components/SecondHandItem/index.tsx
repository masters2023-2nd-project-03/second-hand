import * as S from './styles';

import Icon from '../Icon';
import elapsedTime from '../../utils/elapsedTime';
import { formatNumber } from '../../utils/formatNumber';
import IconWithCount from './IconWithCount';

interface SecondHandItemProps {
  title: string;
  salesStatus: '판매중' | '예약중' | '판매완료';
  updatedAt: string;
  price: number | null;
  location: string;
  chatRoomCount: number;
  watchListCount: number;
  isWatchlistChecked: boolean;
  productMainImgUrl: string;
  isSetEditOption?: boolean;
}

const SecondHandItem = ({
  title,
  updatedAt,
  salesStatus,
  price,
  location,
  chatRoomCount,
  watchListCount,
  isWatchlistChecked,
  productMainImgUrl,
  isSetEditOption,
}: SecondHandItemProps) => {
  return (
    <S.ItemContainer>
      <S.ItemImage imageURI={productMainImgUrl} />
      <S.ItemInfo>
        <S.ItemContents>
          <S.Content>
            <S.ColumnTop>{title}</S.ColumnTop>
            <S.ColumnMid>
              {location} ・ {elapsedTime(updatedAt)}
            </S.ColumnMid>
            <S.ColumnBot>
              {salesStatus !== '판매중' && (
                <S.StatusLabel round={true} status={salesStatus}>
                  {salesStatus}
                </S.StatusLabel>
              )}
              {price !== null && <S.Price>{formatNumber(price)}원</S.Price>}
            </S.ColumnBot>
          </S.Content>
          <div className="Option">
            {isSetEditOption && (
              <Icon name={'ellipsis'} width={'17'} height={'20'} />
            )}
          </div>
        </S.ItemContents>
        <S.ItemIssue>
          <IconWithCount name={'message'} count={chatRoomCount} />
          <S.HeartIconWithCount>
            {isWatchlistChecked === true ? (
              <IconWithCount
                name={'heart'}
                count={watchListCount}
                fill={'black'}
              />
            ) : (
              <IconWithCount
                name={'heartFill'}
                count={watchListCount}
                fill={'red'}
              />
            )}
          </S.HeartIconWithCount>
        </S.ItemIssue>
      </S.ItemInfo>
    </S.ItemContainer>
  );
};

export default SecondHandItem;
