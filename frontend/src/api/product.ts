import { axiosInstanceWithoutBearer, axiosInstanceWithBearer } from './axios';
import { PostObjectType } from '../context/SalesItem/useContext';

export const getProducts = async () => {
  const res = await axiosInstanceWithoutBearer.get(`/api/products?`);
  return res;
};

export const postProducts = async (formData: PostObjectType, token: string) => {
  const res = await axiosInstanceWithBearer.post(`/api/products`, formData, {
    headers: {
      Authorization: `Bearer ${token}`,
      'Content-Type': 'multipart/form-data',
    },
  });
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

export const getProductDetail = async (
  productsId: string | undefined,
  token: string | null,
) => {
  if (!productsId) {
    throw new Error('productsId is undefined');
  }

  const res = await axiosInstanceWithBearer.get(`/api/products/${productsId}`, {
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
