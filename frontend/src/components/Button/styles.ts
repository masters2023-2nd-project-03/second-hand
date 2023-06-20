import styled, { css } from 'styled-components';

import { ButtonProps } from '.';

export const Button = styled.button<ButtonProps>`
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 4px;
  padding: 8px 16px;
  border-radius: 8px;
  ${({ theme }) => theme.typo.caption1}

  ${({ active, theme }) =>
    active
      ? css`
          ${theme.color.accentText};
          ${theme.color.accentBackgroundPrimary};
        `
      : css`
          ${theme.color.accentTextWeak};
          ${theme.color.systemBackground};
          ${theme.color.neutralBorder};
        `};

  ${({ circle }) =>
    circle &&
    css`
      height: 56px;
      width: 56px;
      border-radius: 50%;
      padding: 10px;
    `}

  ${({ round }) =>
    round &&
    css`
      border-radius: 50px;
    `}

  ${({ fullWidth, theme }) =>
    fullWidth &&
    css`
      width: 100%;
      padding: 16px 20px;
      ${theme.typo.subhead};
    `}

  ${({ spaceBetween }) =>
    spaceBetween &&
    css`
      justify-content: space-between;
    `}
`;
