import { axiosInstanceWithoutBearer, axiosInstanceWithBearer } from './axios';

export const postJoin = async (
  nickname: string,
  profileUrl: File,
  oauthId: string,
) => {
  const formData = new FormData();
  formData.append('nickname', nickname);
  formData.append('oauthId', oauthId);
  formData.append('profileUrl', profileUrl, profileUrl.name);

  const res = await axiosInstanceWithoutBearer.post(
    `/api/members/join`,
    formData,
    {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    },
  );

  return res;
};

export const getMembers = async (token: string | null) => {
  const res = await axiosInstanceWithBearer.get(`/api/members`, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  return res;
};

export const getSeller = async (
  token: string | null,
  curRoomId: string | undefined,
) => {
  const res = await axiosInstanceWithBearer.get(`chat/room/${curRoomId}`, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  return res;
};

export const patchMembersLocation = async (
  token: string | null,
  locationIdList: number[],
) => {
  const res = await axiosInstanceWithBearer.patch(
    `/api/members/locations`,
    {
      locationIdList,
    },
    {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    },
  );

  return res;
};
