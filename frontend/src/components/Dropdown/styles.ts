import styled from 'styled-components';

export const dropdownContainer = styled.div``;

export const dropdownWrapper = styled.div`
  position: relative;
`;

export const dropdownHeader = styled.div`
  display: flex;
  align-items: center;
  gap: 10px;
`;

export const selectedOption = styled.div`
  ${({ theme }) => theme.typo.headline}
`;

export const panelContainer = styled.div`
  position: absolute;
  z-index: 9999;
  width: 240px;
  ${({ theme }) => theme.color.neutralBackground}
  ${({ theme }) => theme.color.neutralBorderStrong}
  border-radius: 12px;
  margin-top: 4px;
`;
