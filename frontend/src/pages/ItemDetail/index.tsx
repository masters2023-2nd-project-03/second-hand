import { useParams } from 'react-router-dom';

const ItemDetail = () => {
  const { productsId } = useParams();

  return (
    <>
      <div>현재 제품은 {productsId} 번 입니다.</div>
      <div className="DetailSliderPhotos">제품 사진</div>
      <div className="DetaulSellerInfo">판매자 정보</div>
      <div className="DetailItem">물품 내용</div>
      <div className="DetailTapBar">관심 + 채팅</div>
    </>
  );
};

export default ItemDetail;
