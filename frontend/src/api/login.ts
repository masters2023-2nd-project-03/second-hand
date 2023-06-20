import { axiosInstanceWithoutBearer } from './axios';

export const postLogin = async (code: string | null) => {
  if (!code) throw new Error("Code couldn't be null");

  // TODO (시저) : api 나오면 상수로 빼고 body 넣기
  const res = await axiosInstanceWithoutBearer.post('/api/oauth2/login', {});

  return res;
};
