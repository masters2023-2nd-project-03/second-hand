import * as S from './styles';

interface SecondHandItemProps {
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

const DetailSliderPhotos = ({
  title,
  updatedAt,
  salesStatus,
  price,
  location,
  chatRoomCount,
  watchlistCount,
  isWatchlistChecked,
  productMainImgUrl,
  option = false,
}: SecondHandItemProps) => {
  return (
    <>
      <S.SliderContainer>
        <S.SliderImage src={productMainImgUrl} />
      </S.SliderContainer>
    </>
  );
};

export default DetailSliderPhotos;
