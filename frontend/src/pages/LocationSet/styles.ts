import styled from 'styled-components';

export const Main = styled.main`
  margin-top: 50px;
  margin-bottom: 80px;
  padding: 0 16px;
  width: 100%;
`;

export const location = styled.li`
  ${({ theme }) => theme.color.neutralText}
  ${({ theme }) => theme.typo.subhead}
  padding-top: 16px;
  padding-bottom: 16px;
  border-bottom: 1px solid rgba(179, 179, 179, 0.39);
  cursor: pointer;
`;
