import styled from 'styled-components';

export const UploadTitle = styled.div`
  padding: 15px 0;
  border-bottom: 1px solid rgba(179, 179, 179, 0.39);
`;

export const Title = styled.div`
  width: 100%;
`;

export const inputTitle = styled.input`
  border: none;
  outline: none;
  background: transparent;
  box-shadow: none;
  resize: none;
  width: 100%;
  font-weight: 400;
  font-size: 15px;
  line-height: 22px;
  overflow: hidden;
`;

export const Contents = styled.div`
  display: flex;
  justify-content: space-between;
  margin-top: 8px;
`;

export const Categories = styled.div`
  display: flex;
  overflow-x: scroll;

  &::-webkit-scrollbar {
    display: none;
  }
`;

export const Category = styled.div<{ isActive: boolean }>`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  padding: 8px 16px;
  flex-shrink: 0;
  border: 1px solid rgba(179, 179, 179, 0.39);
  border-radius: 50px;
  margin-right: 4px;

  background: #ffffff;
  font-weight: 400;
  font-size: 12px;
  line-height: 16px;

  background: ${({ isActive }) => (isActive ? '#FF9500' : '#ffffff')};
  color: ${({ isActive }) => (isActive ? '#ffffff' : '#000000')};
`;

export const MoreIcon = styled.div`
  /* flex-shrink: 0; */
  display: flex;
  align-items: center;
`;
