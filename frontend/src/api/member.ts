import axios from 'axios';
import { BASE_URL } from '../constants/api';
import { axiosInstanceWithBearer } from './axios';

export const postJoin = async (
  nickname: string,
  profileUrl: File,
  oauthId: string,
) => {
  const formData = new FormData();
  formData.append('nickname', nickname);
  formData.append('oauthId', oauthId);
  formData.append('profileUrl', profileUrl, profileUrl.name);

  const res = await axios.post(
    `http://52.79.159.39:8080/api/members/join`,
    formData,
    {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    },
  );

  return res;
};

export const getMember = async () => {
  const res = await axiosInstanceWithBearer.get(
    `http://52.79.159.39:8080/api/members`,
  );

  return res;
};
