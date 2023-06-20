import { axiosInstanceWithoutBearer } from './axios';

export const getCategory = async () => {
  const res = await axiosInstanceWithoutBearer.get('/api/categories');

  return res;
};
