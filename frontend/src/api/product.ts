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
