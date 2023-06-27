import styled from 'styled-components';

export const DropdownContainer = styled.div``;

export const DropdownWrapper = styled.div`
  position: relative;
`;

export const DropdownHeader = styled.div`
  display: flex;
  align-items: center;
  gap: 10px;
`;

export const SelectedOption = styled.div`
  ${({ theme }) => theme.typo.headline}
`;

export const PanelContainer = styled.div`
  position: absolute;
  z-index: 9999;
  width: 240px;
  ${({ theme }) => theme.color.neutralBackground}
  ${({ theme }) => theme.color.neutralBorderStrong}
  border-radius: 12px;
  margin-top: 4px;
`;
