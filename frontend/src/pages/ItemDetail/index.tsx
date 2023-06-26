import { useParams } from 'react-router-dom';

import DetailSliderPhotos from '../../components/DetailSliderPhotos';
import NavBarTitle from '../../components/NavBarTitle';
import * as S from './styles';

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

const ItemDetail = () => {
  const { productsId } = useParams();

  // Todo : 컴포넌트 제작을 위한 샘플 아이템 (추후 삭제)
  const sampleItem: Item = {
    productId: 100,
    title: '춘식이 키링 팝니다! (급처)',
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
      <NavBarTitle backIcon moreIcon type="low" />
      <DetailSliderPhotos
        title={sampleItem.title}
        updatedAt={sampleItem.updatedAt}
        salesStatus={sampleItem.salesStatus}
        price={sampleItem.price}
        location={sampleItem.location}
        chatRoomCount={sampleItem.chatRoomCount}
        watchlistCount={sampleItem.watchlistCount}
        isWatchlistChecked={sampleItem.isWatchlistChecked}
        productMainImgUrl={sampleItem.productMainImgUrl}
        option={false}
      />
      <S.Main>
        <div className="DetaulSellerInfo">판매자 정보</div>
        <div className="DetailItem">물품 내용</div>
        <div className="DetailTapBar">관심 + 채팅</div>
      </S.Main>
      <div>현재 제품은 {productsId} 번 입니다.</div>
    </>
  );
};

export default ItemDetail;
