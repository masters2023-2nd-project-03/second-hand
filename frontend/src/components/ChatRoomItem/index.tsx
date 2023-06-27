import * as S from './styles';
import formatNumber from '../../utils/formatNumber';

interface Item {
  productId: number;
  createAt: string;
  title: string;
  salesStatus: '판매중' | '예약중' | '판매완료';
  updatedAt: string;
  price: number | null;
  location: string;
  chatRoomCount: number;
  watchlistCount: number;
  isWatchlistChecked: boolean;
  productMainImgUrl: string;
  option?: boolean;
}

const ChatRoomItem = () => {
  const sampleItem: Item = {
    productId: 100,
    title: '시저 가져가세요!! (급처)',
    salesStatus: '판매중',
    createAt: '2023-06-22T08:35:03',
    updatedAt: '2023-06-22T08:35:03',
    price: 9900,
    location: '역삼1동',
    chatRoomCount: 2,
    watchlistCount: 3,
    isWatchlistChecked: false,
    productMainImgUrl:
      'https://second-hand-s3.s3.ap-northeast-2.amazonaws.com/product_images/product_100-1',
  };

  return (
    <>
      <S.ItemContainer>
        <S.ItemImage imageURI={sampleItem.productMainImgUrl} />
        <S.ItemInfo>
          <S.ColumnTop>{sampleItem.title}</S.ColumnTop>
          <S.ColumnBot>
            {sampleItem.price !== null && (
              <S.Price>{formatNumber(sampleItem.price)}원</S.Price>
            )}
          </S.ColumnBot>
        </S.ItemInfo>
      </S.ItemContainer>
    </>
  );
};

export default ChatRoomItem;
