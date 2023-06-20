import { useNavigate } from 'react-router-dom';

import NavBarTitle from '../../components/NavBarTitle';
import * as S from './styles';

const CatalogPage = () => {
  const navigate = useNavigate();

  const handleBackClick = () => {
    navigate(-1);
  };

  return (
    <>
      <NavBarTitle
        prevTitle="닫기"
        type="low"
        backIcon
        preTitleClick={handleBackClick}
        centerTitle="카탈로그"
      />
      {/*TODO(sarang_daddy) : API 나오면 수정해야함*/}
      <S.Catalog>
        <S.Item>
          <img
            src="https://second-hand-s3.s3.ap-northeast-2.amazonaws.com/category_img/category_01.PNG"
            alt="Item 1"
            width="44"
            height="44"
          />
          <span>디지털기기</span>
        </S.Item>
        <S.Item>Item 2</S.Item>
        <S.Item>Item 3</S.Item>
        <S.Item>Item 4</S.Item>
        <S.Item>Item 5</S.Item>
        <S.Item>Item 6</S.Item>
        <S.Item>Item 7</S.Item>
        <S.Item>Item 8</S.Item>
        <S.Item>Item 9</S.Item>
        <S.Item>Item 10</S.Item>
        <S.Item>Item 11</S.Item>
        <S.Item>Item 12</S.Item>
        <S.Item>Item 13</S.Item>
        <S.Item>Item 14</S.Item>
        <S.Item>Item 15</S.Item>
        <S.Item>Item 16</S.Item>
        <S.Item>Item 17</S.Item>
        <S.Item>Item 18</S.Item>
        <S.Item>Item 19</S.Item>
      </S.Catalog>
    </>
  );
};

export default CatalogPage;
