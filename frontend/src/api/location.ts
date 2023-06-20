import { axiosInstanceWithoutBearer } from './axios';

export const getLocation = async () => {
  const res = await axiosInstanceWithoutBearer.get('/api/locations');

  return res;
};
