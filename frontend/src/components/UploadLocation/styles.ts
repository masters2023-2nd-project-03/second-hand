import styled from 'styled-components';

export const UploadLocationContainer = styled.div`
  display: flex;
  position: fixed;
  bottom: 0px;
  height: 80px;
  justify-content: space-between;
  width: 100%;
  background: #fafafa;
  ${({ theme }) => theme.color.neutralBorder}
  padding: 7px 16px 0 16px;
`;

export const Menu = styled.div`
  width: 100%;
  display: flex;
  justify-content: space-between;
`;

export const Left = styled.div`
  display: flex;
  gap: 5px;

  font-size: 13px;
  font-weight: 400;
`;

export const Right = styled.div``;
