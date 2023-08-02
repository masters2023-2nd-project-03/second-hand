import * as S from './styles';

import { formatNumber } from '../../utils/formatNumber';
import useAsync from '../../hooks/useAsync';
import { ACCESS_TOKEN } from '../../constants/login';
import { getProductsDetail } from '../../api/product';

interface ChatRoomItemProps {
  curProductsId: string | undefined;
}
interface Item {
  productId: number;
  createAt: string;
  title: string;
  contents: string;
  salesStatus: '판매중' | '예약중' | '판매완료';
  updatedAt: string;
  price: number | null;
  location: string;
  chatRoomCount: number;
  watchlistCount: number;
  isWatchlistChecked: boolean;
  imageList: string[];
  categoryTitle: string;
}

const ChatRoomItem = ({ curProductsId }: ChatRoomItemProps) => {
  const accessToken = localStorage.getItem(ACCESS_TOKEN);
  const { data } = useAsync(() =>
    getProductsDetail(curProductsId, accessToken),
  );

  const selectedItem: Item = data?.data;

  return (
    <>
      <S.ItemContainer>
        <S.ItemImage imageURI={selectedItem?.imageList[0]} />
        <S.ItemInfo>
          <S.ColumnTop>{selectedItem?.title}</S.ColumnTop>
          <S.ColumnBot>
            {selectedItem?.price !== null && (
              <S.Price>{formatNumber(Number(selectedItem?.price))}원</S.Price>
            )}
          </S.ColumnBot>
        </S.ItemInfo>
      </S.ItemContainer>
    </>
  );
};

export default ChatRoomItem;
