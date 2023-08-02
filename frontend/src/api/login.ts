import { axiosInstanceWithoutBearer } from './axios';

export const postLogin = async (code: string | null) => {
  if (!code) throw new Error("Code couldn't be null");

  const res = await axiosInstanceWithoutBearer.post(`/api/oauth2/login`, {
    code: code,
    platform: 'github',
  });

  return res;
};
