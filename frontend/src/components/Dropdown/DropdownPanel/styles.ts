import styled from 'styled-components';

export const DropdownPanel = styled.div<{
  isLastPanel?: boolean;
  mainLocationState?: boolean;
}>`
  width: 240px;
  height: 45px;
  display: flex;
  align-items: center;
  border-bottom: 1px solid rgba(179, 179, 179, 0.39);
  ${({ isLastPanel }) => isLastPanel && `border-bottom: none;`}

  z-index: 4;
`;

export const OptionTitle = styled.span<{
  isLastPanel?: boolean;
  mainLocationState?: boolean;
}>`
  ${({ theme }) => theme.typo.subhead}
  font-weight: ${({ mainLocationState }) =>
    mainLocationState ? '600' : '400'};
  ${({ theme }) => theme.color.neutralTextStrong}
  margin-left: 16px;
`;
