import axios from 'axios';
import { BASE_URL } from '../constants/api';

export const postJoin = async (
  nickname: string,
  profileUrl: File,
  oauthId: string,
) => {
  const formData = new FormData();
  formData.append('nickname', nickname);
  formData.append('oauthId', oauthId);
  formData.append('profileUrl', profileUrl, profileUrl.name);

  //  TODO (시저) : 서버 배포후 주석 해제하고 밑에 axios 주석........
  const res = await axios.post(
    `http://3.39.207.31:8080/api/members/join`,
    formData,
    {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    },
  );
  console.log(res);
  return res;
};
