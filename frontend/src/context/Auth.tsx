import {
  createContext,
  useState,
  useEffect,
  ReactNode,
  useContext,
} from 'react';

import { ACCESS_TOKEN } from '../constants/login';
import { getMember } from '../api/member';

interface AuthProviderProps {
  children: ReactNode;
}

interface UserInfo {
  locationDatas: Array<any>;
  nickname: string;
  profileUrl: string;
}

interface AuthContextValue {
  isLoggedIn: boolean;
  userInfo: UserInfo;
  handleLogin: (accessToken: string) => void;
  handleLogout: () => void;
}

const AuthContext = createContext<AuthContextValue | null>(null);

const AuthProvider = ({ children }: AuthProviderProps) => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [userInfo, setUserInfo] = useState({
    nickname: '',
    profileUrl: '',
    locationDatas: [],
  });

  const handleLogin = (accessToken: string) => {
    localStorage.setItem(ACCESS_TOKEN, accessToken);
    setIsLoggedIn(true);
  };

  const handleLogout = () => {
    localStorage.removeItem(ACCESS_TOKEN);
    setIsLoggedIn(false);
  };

  useEffect(() => {
    const accessToken = localStorage.getItem(ACCESS_TOKEN);
    setIsLoggedIn(!!accessToken);
  }, []);

  useEffect(() => {
    if (!isLoggedIn) {
      setUserInfo({
        nickname: '',
        profileUrl: '',
        locationDatas: [],
      });

      return;
    }

    const fetchUserInfo = async () => {
      const res = await getMember();
      const { data } = res;

      setUserInfo({
        nickname: data.data.nickname,
        profileUrl: data.data.profileUrl,
        locationDatas: data.data.locationDatas,
      });
    };

    fetchUserInfo();
  }, [isLoggedIn]);

  return (
    <AuthContext.Provider
      value={{ isLoggedIn, userInfo, handleLogin, handleLogout }}
    >
      {children}
    </AuthContext.Provider>
  );
};

const useAuthContext = (): AuthContextValue => {
  const context = useContext(AuthContext);

  if (!context) {
    throw new Error('useAuthContext must be used within an AuthProvider');
  }

  return context;
};

export { AuthProvider, useAuthContext };
