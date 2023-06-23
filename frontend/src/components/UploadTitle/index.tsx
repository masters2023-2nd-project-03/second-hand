import { useEffect, useState, useContext } from 'react';

import {
  postSalesItemContext,
  PostObjectType,
} from '../../context/SalesItem/useContext';
import * as S from './styles';
import Icon from '../Icon';
import useAsync from '../../hooks/useAsync';
import { getCategory } from '../../api/category';

interface Category {
  categoryId: number;
  title: string;
  categoryImgUrl: string;
}

const UploadTitle = () => {
  const { data } = useAsync(getCategory);
  const categoryList: Category[] = data?.data;

  const [selectedCategories, setSelectedCategories] = useState<Category[]>([]);
  const [hasInputValue, setHasInputValue] = useState<boolean>(false);
  const [chooseCategory, setChooseCategory] = useState<number | null>(null);

  const { postObject, setPostObject } = useContext(postSalesItemContext);

  useEffect(() => {
    if (hasInputValue) {
      const shuffledCategories = categoryList?.sort(() => 0.5 - Math.random());
      const selectedCategories = shuffledCategories?.slice(0, 3);
      setSelectedCategories(selectedCategories);
    } else {
      setPostObject((prevPostObject: PostObjectType) => ({
        ...prevPostObject,
        title: null,
        categoryId: null,
      }));
    }
  }, [hasInputValue]);

  const handleTitleInput = (event: React.ChangeEvent<HTMLInputElement>) => {
    const inputValue = event.target.value;
    const hasValue = inputValue !== '';

    if (hasValue) {
      setHasInputValue(true);
    } else {
      setHasInputValue(false);
      setSelectedCategories([]);
      setChooseCategory(null);
    }

    setPostObject((prevPostObject: PostObjectType) => ({
      ...prevPostObject,
      title: inputValue,
    }));
  };

  const handleCategoryChoice = (categoryId: number) => {
    setChooseCategory(categoryId);

    setPostObject((prevPostObject: PostObjectType) => ({
      ...prevPostObject,
      categoryId: categoryId,
    }));
  };

  return (
    <>
      <S.UploadTitle>
        <S.Title>
          <S.inputTitle
            placeholder="글제목"
            type="text"
            onChange={handleTitleInput}
          />
        </S.Title>
        {hasInputValue && (
          <S.Contents>
            <S.Categories>
              {selectedCategories.map((category) => (
                <S.Category
                  key={category.categoryId}
                  onClick={() => handleCategoryChoice(category.categoryId)}
                  isActive={chooseCategory === category.categoryId}
                >
                  {category.title}
                </S.Category>
              ))}
            </S.Categories>
            <S.MoreIcon>
              {/*TODO(sarang_daddy) : 카테고리 목록 리스트로 이동 추가해야함.*/}
              <Icon name="chevronRight" height="11" width="22" />
            </S.MoreIcon>
          </S.Contents>
        )}
      </S.UploadTitle>
    </>
  );
};

export default UploadTitle;
