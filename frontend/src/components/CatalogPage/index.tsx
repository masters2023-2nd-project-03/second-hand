import { useNavigate } from 'react-router-dom';

import NavBarTitle from '../NavBarTitle';
import * as S from './styles';

const CatalogPage = () => {
  const navigate = useNavigate();

  const handleBackClick = () => {
    navigate(-1);
  };

  //TODO(sarang_daddy) : onClick 테스트를 위한 코드 추후 삭제
  const checkTheMoreClick = () => {
    console.log('완료 버튼을 클릭했습니다.');
  };

  return (
    <>
      <NavBarTitle
        prevTitle="닫기"
        type="low"
        backIcon
        preTitleClick={handleBackClick}
        rightTitleClick={checkTheMoreClick}
        centerTitle="카탈로그"
        rightTitle="완료"
      />
      <S.Catalog className="catalog">
        <S.Item className="item">Item 1</S.Item>
        <S.Item className="item">Item 2</S.Item>
        <S.Item className="item">Item 3</S.Item>
        <S.Item className="item">Item 4</S.Item>
        <S.Item className="item">Item 5</S.Item>
        <S.Item className="item">Item 6</S.Item>
        <S.Item className="item">Item 7</S.Item>
        <S.Item className="item">Item 8</S.Item>
        <S.Item className="item">Item 9</S.Item>
        <S.Item className="item">Item 10</S.Item>
      </S.Catalog>
    </>
  );
};

export default CatalogPage;
