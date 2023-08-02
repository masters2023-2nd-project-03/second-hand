import styled from 'styled-components';

interface PanelContainerProps {
  isReverse: boolean;
}

export const DropdownContainer = styled.div`
  z-index: 3;
`;

export const DropdownWrapper = styled.div`
  width: 100%;
  height: 100%;
  position: fixed;
  top: 0px;
  left: 0px;
  background-color: transparent;
  z-index: 2;
`;

export const DropdownHeader = styled.div`
  display: flex;
  align-items: center;
  gap: 10px;
`;

export const SelectedOption = styled.div`
  ${({ theme }) => theme.typo.headline}
`;

export const PanelContainer = styled.div<PanelContainerProps>`
  position: absolute;
  width: 240px;
  ${({ theme }) => theme.color.neutralBackground}
  ${({ theme }) => theme.color.neutralBorderStrong}
  border-radius: 12px;
  margin-top: 4px;

  bottom: ${({ isReverse }) => (isReverse ? '80px' : 'none')};
`;
