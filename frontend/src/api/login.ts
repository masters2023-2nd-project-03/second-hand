import axios from 'axios';

export const postLogin = async (code: string | null) => {
  if (!code) throw new Error("Code couldn't be null");

  // TODO (시저) :상수로 빼기
  const res = await axios.post(`http://3.39.207.31:8080/api/oauth2/login`, {
    code: code,
    platform: 'github',
  });

  console.log(res);

  return res;
};
