import { axiosInstanceWithoutBearer } from './axios';

export const getProducts = async (locationId: number) => {
  const res = await axiosInstanceWithoutBearer.get(
    `/api/products?location-id=${locationId}`,
  );

  return res;
};
