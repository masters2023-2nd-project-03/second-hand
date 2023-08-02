import * as S from './styles';

interface SecondHandItemProps {
  imageList: string;
}

// TODO : 아이템 이미지 리스트 다 가져오기

const DetailSliderPhotos = ({ imageList }: SecondHandItemProps) => {
  return (
    <>
      <S.SliderContainer>
        <S.SliderImage src={imageList} />
      </S.SliderContainer>
    </>
  );
};

export default DetailSliderPhotos;
