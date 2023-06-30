import styled from 'styled-components';

export const UploadPhotoContainer = styled.div`
  margin-top: 66px;
  display: flex;
  border-bottom: solid 1px rgba(179, 179, 179, 0.39);
  padding-bottom: 16px;
`;

export const UploadInput = styled.input`
  display: none;
  margin-top: 16px;
`;

export const UploadIcon = styled.div`
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: center;
  margin-right: 16px;
  width: 80px;
  height: 80px;
  flex-shrink: 0;
  border-radius: 12px;
  border: 1px solid rgba(179, 179, 179, 0.39);
  margin-top: 5px;

  span {
    margin-top: 10px;
  }
`;

export const Photos = styled.div`
  display: flex;
  overflow-x: scroll;

  &::-webkit-scrollbar {
    display: none;
  }
`;

export const PhotoElement = styled.div`
  position: relative;
  margin-top: 5px;
`;

export const Photo = styled.img`
  width: 80px;
  height: 80px;
  margin-right: 16px;
  border-radius: 12px;
  border: 1px solid rgba(179, 179, 179, 0.39);
`;

export const DeleteBtn = styled.div`
  width: 28px;
  height: 28px;
  border-radius: 50%;
  background-color: black;
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: -5px;
  right: 10px;
`;
