import { ReactNode } from 'react';
import { Navigate } from 'react-router-dom';
import { useAuthContext } from '../../context/Auth';

interface ProtectedRouteProps {
  children: ReactNode;
}

const ProtectedRoute = ({ children }: ProtectedRouteProps) => {
  const { isLoggedIn } = useAuthContext();

  return isLoggedIn ? <>{children}</> : <Navigate to={'/login'} replace />;
};
export default ProtectedRoute;
