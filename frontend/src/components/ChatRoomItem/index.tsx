import { useParams, useNavigate } from 'react-router-dom';

import * as S from './styles';
import formatNumber from '../../utils/formatNumber';
import useAsync from '../../hooks/useAsync';
import { ACCESS_TOKEN } from '../../constants/login';
import { getProductDetail } from '../../api/product';

interface ChatRoomItemProps {
  productsId: string | undefined;
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

const ChatRoomItem = ({ productsId }: ChatRoomItemProps) => {
  const accessToken = localStorage.getItem(ACCESS_TOKEN);
  const { data } = useAsync(() => getProductDetail(productsId, accessToken));

  const selectedItem: Item = data?.data;

  console.log(productsId);
  console.log(data);
  console.log(selectedItem);

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
