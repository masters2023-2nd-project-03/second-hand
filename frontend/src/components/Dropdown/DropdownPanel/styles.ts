import styled from 'styled-components';

export const dropdownPanel = styled.div<{ isLastPanel?: boolean }>`
  width: 240px;
  height: 45px;
  display: flex;
  align-items: center;
  ${({ theme }) => theme.color.neutralBorder}
  ${({ isLastPanel }) => isLastPanel && `border-bottom: none;`}
`;

export const optionTitle = styled.span<{ isLastPanel?: boolean }>`
  font-weight: ${({ isLastPanel }) => (isLastPanel ? '400' : '510')};
  ${({ theme }) => theme.typo.subhead}
  ${({ theme }) => theme.color.neutralTextStrong}
  margin-left: 16px;
`;
