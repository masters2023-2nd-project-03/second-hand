import { useNavigate } from 'react-router-dom';
import { useState } from 'react';

import * as S from './styles';

import {
  postSalesItemContext,
  PostObjectType,
} from '../../context/SalesItem/useContext';
import NavBarTitle from '../../components/NavBarTitle';
import UploadPhoto from '../../components/UploadPhoto';
import UploadTitle from '../../components/UploadTitle';
import UploadPrice from '../../components/UploadPrice';
import UploadComment from '../../components/UploadComment';
import UploadLocation from '../../components/UploadLocation';
import { postProducts } from '../../api/product';
// import { ACCESS_TOKEN } from '../../constants/login';

// TODO : 사용자 설정 동네 ID를 가져와야함

const SalesMyItemPage = () => {
  // const accessToken = localStorage.getItem(ACCESS_TOKEN);
  const navigation = useNavigate();

  const handleBackIconClick = () => {
    navigation(-1);
  };

  const initialPostObject: PostObjectType = {
    title: null,
    price: null,
    content: null,
    categoryId: null,
    locationId: 18, //TODO : 로그인 및 동네설정 필요
    files: null,
  };
  const [postObject, setPostObject] = useState(initialPostObject);

  const handleUploadComplete = async () => {
    const formData = new FormData();

    formData.append('title', postObject.title ?? '');
    formData.append('price', String(postObject.price) ?? '');
    formData.append('content', postObject.content ?? '');
    formData.append('categoryId', String(postObject.categoryId) ?? '');
    formData.append('locationId', String(postObject.locationId) ?? '');

    if (postObject.files) {
      postObject.files.forEach((file) => {
        file.forEach((value, name) => {
          formData.append(name, value);
        });
      });
    }

    // TODO : 토큰 검토 후 상품 등록 되도록 바뀌어야 함
    await postProducts(formData);
    navigation(-1);
  };

  return (
    <>
      <S.Main>
        <postSalesItemContext.Provider value={{ postObject, setPostObject }}>
          <NavBarTitle
            prevTitle="닫기"
            type="high"
            preTitleClick={handleBackIconClick}
            rightTitleClick={handleUploadComplete}
            centerTitle="내 물건 팔기"
            rightTitle="완료"
          />
          <UploadPhoto />
          <UploadTitle />
          <UploadPrice />
          <UploadComment />
        </postSalesItemContext.Provider>
      </S.Main>
      <UploadLocation />
    </>
  );
};

export default SalesMyItemPage;
