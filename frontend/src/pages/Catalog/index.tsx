import { useNavigate } from 'react-router-dom';

import NavBarTitle from '../../components/NavBarTitle';
import * as S from './styles';
import useAsync from '../../hooks/useAsync';
import { getCategory } from '../../api/category';

interface Category {
  categoryId: number;
  title: string;
  categoryImgUrl: string;
}

const CatalogPage = () => {
  const navigate = useNavigate();

  const handleBackClick = () => {
    navigate(-1);
  };

  const { data } = useAsync(getCategory);
  const categoryList = data?.data;

  return (
    <>
      <NavBarTitle
        prevTitle="닫기"
        type="high"
        backIcon
        preTitleClick={handleBackClick}
        centerTitle="카탈로그"
      />
      <S.Categories>
        {categoryList?.map((category: Category) => {
          return (
            <S.Category key={category.categoryId}>
              <img src={category.categoryImgUrl} width="44" height="44" />
              <span>{category.title}</span>
            </S.Category>
          );
        })}
      </S.Categories>
    </>
  );
};

export default CatalogPage;
