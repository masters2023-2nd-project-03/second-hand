import { useParams, useNavigate } from 'react-router-dom';

import NavBarTitle from '../../components/NavBarTitle';
import DetailSliderPhotos from '../../components/DetailSliderPhotos';
import DetailItem from '../../components/DetailItem';
import DetailTapBar from '../../components/DetailTapBar';
import * as S from './styles';
import useAsync from '../../hooks/useAsync';
import { ACCESS_TOKEN } from '../../constants/login';
import { getProductDetail } from '../../api/product';

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

const ItemDetail = () => {
  const { productsId } = useParams();
  const curProductsId: string | undefined = productsId;

  const accessToken = localStorage.getItem(ACCESS_TOKEN);
  const { data } = useAsync(() => getProductDetail(productsId, accessToken));

  const selectedItem: Item = data?.data;

  const navigation = useNavigate();

  const handleBackIconClick = () => {
    navigation(-1);
  };

  return (
    <>
      <NavBarTitle
        backIcon
        moreIcon
        type="low"
        preTitleClick={handleBackIconClick}
      />
      {selectedItem && (
        <DetailSliderPhotos
          title={selectedItem.title}
          updatedAt={selectedItem.updatedAt}
          salesStatus={selectedItem.salesStatus}
          price={selectedItem.price}
          location={selectedItem.location}
          chatRoomCount={selectedItem.chatRoomCount}
          watchlistCount={selectedItem.watchlistCount}
          isWatchlistChecked={selectedItem.isWatchlistChecked}
          imageList={selectedItem.imageList[0]}
        />
      )}
      <S.Main>
        <div>현재 제품은 {productsId} 번 입니다.</div>
        <div className="DetaulSellerInfo">판매자 정보</div>
        {selectedItem && (
          <DetailItem
            title={selectedItem.title}
            updatedAt={selectedItem.updatedAt}
            salesStatus={selectedItem.salesStatus}
            price={selectedItem.price}
            location={selectedItem.location}
            chatRoomCount={selectedItem.chatRoomCount}
            watchlistCount={selectedItem.watchlistCount}
            isWatchlistChecked={selectedItem.isWatchlistChecked}
            imageList={selectedItem.imageList[0]}
            categoryTitle={selectedItem.categoryTitle}
            contents={selectedItem.contents}
          />
        )}
      </S.Main>

      {selectedItem && (
        <DetailTapBar
          curProductsId={curProductsId}
          price={selectedItem.price}
        />
      )}
    </>
  );
};

export default ItemDetail;
