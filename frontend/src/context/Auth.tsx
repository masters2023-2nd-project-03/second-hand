import {
  createContext,
  useState,
  useEffect,
  ReactNode,
  useContext,
} from 'react';

import { ACCESS_TOKEN } from '../constants/login';

interface AuthProviderProps {
  children: ReactNode;
}

interface AuthContextValue {
  isLoggedIn: boolean;
  handleLogin: (accessToken: string) => void;
  handleLogout: () => void;
}

const AuthContext = createContext<AuthContextValue | null>(null);

const AuthProvider = ({ children }: AuthProviderProps) => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  useEffect(() => {
    const accessToken = localStorage.getItem(ACCESS_TOKEN);
    setIsLoggedIn(!!accessToken);
  }, []);

  const handleLogin = (accessToken: string) => {
    localStorage.setItem(ACCESS_TOKEN, accessToken);
    setIsLoggedIn(true);
  };

  const handleLogout = () => {
    localStorage.removeItem(ACCESS_TOKEN);
    setIsLoggedIn(false);
  };

  return (
    <AuthContext.Provider value={{ isLoggedIn, handleLogin, handleLogout }}>
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
