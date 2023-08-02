import styled from 'styled-components';

export const SearchBarContainer = styled.div`
  width: 100%;
  display: flex;
  align-items: center;
  padding: 7px;
  border-radius: 10px;
  margin-top: 60px;
  ${({ theme }) => theme.color.systemBackgroundWeak}
`;

export const IconWrapper = styled.div`
  margin-right: 4px;
`;

export const Input = styled.input`
  width: 100%;
  ${({ theme }) => theme.typo.subhead}
  ${({ theme }) => theme.color.neutralText}
  ${({ theme }) => theme.color.systemBackgroundWeak}
`;
