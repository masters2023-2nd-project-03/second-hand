import styled from 'styled-components';

export const dropdownPanel = styled.div<{ isLastPanel?: boolean }>`
  width: 240px;
  height: 45px;
  display: flex;
  align-items: center;
  border-bottom: solid 1px rgba(179, 179, 179, 0.39);
  ${({ isLastPanel }) => isLastPanel && `border-bottom: none;`}
`;

export const optionTitle = styled.span`
  font-weight: 510;
  font-size: 15px;
  line-height: 22px;
  color: #000000;
  margin-left: 16px;
`;
