import styled from 'styled-components';

export const dropdownPanel = styled.div<{ isLastPanel?: boolean }>`
  width: 240px;
  height: 45px;
  display: flex;
  align-items: center;
  border-bottom: 1px solid rgba(179, 179, 179, 0.39);
  ${({ isLastPanel }) => isLastPanel && `border-bottom: none;`}
`;

export const optionTitle = styled.span<{ isLastPanel?: boolean }>`
  ${({ theme }) => theme.typo.subhead}
  font-weight: ${({ isLastPanel }) => (isLastPanel ? '400' : '510')};
  ${({ theme }) => theme.color.neutralTextStrong}
  margin-left: 16px;
`;
