import { HTMLAttributes, ReactNode } from 'react';

import * as S from './styles';

export interface ButtonProps extends HTMLAttributes<HTMLButtonElement> {
  children: ReactNode;
  round?: boolean;
  active?: boolean;
  spaceBetween?: boolean;
  fullWidth?: boolean;
  circle?: boolean;
}

const Button = ({ children, ...rest }: ButtonProps) => {
  return <S.Button {...rest}>{children}</S.Button>;
};

export default Button;
