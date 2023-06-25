import { useNavigate } from 'react-router-dom';
import { useState } from 'react';

import axios from 'axios';

import {
  postSalesItemContext,
  PostObjectType,
} from '../../context/SalesItem/useContext';
import * as S from './styles';
import NavBarTitle from '../../components/NavBarTitle';
import UploadPhoto from '../../components/UploadPhoto';
import UploadTitle from '../../components/UploadTitle';
import UploadPrice from '../../components/UploadPrice';
import UploadComment from '../../components/UploadComment';
import { BASE_URL } from '../../constants/api';
import TabBar from '../../components/TabBar';

const SalesItemPage = () => {
  const navigation = useNavigate();

  const handleBackIconClick = () => {
    navigation(-1);
  };

  const initialPostObject: PostObjectType = {
    title: null,
    price: null,
    content: '', //TODO(sarang_daddy) : content 컴포넌트 필요
    categoryId: null,
    locationId: 18, //TODO(시저) : 로그인 및 동네설정 필요
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

    //TODO(sarang_daddy) : 커스텀훅으로 수정해야함
    try {
      const token =
        'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjg3MTAyMjc1LCJleHAiOjE2ODg5MDIyNzV9.AhaCUeK_M_Ph3dVTf4VCceB-Wk2AWp1ukYdP5G4VpCU';

      const response = await axios.post(`${BASE_URL}/api/products`, formData, {
        headers: {
          Authorization: `Bearer ${token}`,
          'Content-Type': 'multipart/form-data',
        },
      });

      if (response.status === 200) {
        console.log('POST 요청이 성공적으로 완료되었습니다.');
      } else {
        console.log('POST 요청이 실패하였습니다.');
      }
    } catch (error) {
      console.log('POST 요청 중 오류가 발생하였습니다.', error);
    }

    navigation(-1);
  };

  return (
    <>
      <S.Main>
        <postSalesItemContext.Provider value={{ postObject, setPostObject }}>
          <NavBarTitle
            prevTitle="닫기"
            type="low"
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
      <TabBar />
    </>
  );
};

export default SalesItemPage;
