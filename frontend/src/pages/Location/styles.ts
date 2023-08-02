import styled from 'styled-components';

export const Main = styled.main`
  margin-top: 50px;
  margin-bottom: 80px;
  padding: 0 16px;
  width: 100%;
`;

export const LocationContainer = styled.div``;

export const Signboard = styled.div`
  display: flex;
  align-items: center;
  flex-direction: column;
  padding-top: 40px;
  padding-bottom: 48px;

  span {
    ${({ theme }) => theme.typo.footnote}
    ${({ theme }) => theme.color.neutralTextStrong}
  }
`;

export const BtnContainer = styled.div`
  display: flex;
  gap: 10px;
`;

export const DeleteButton = styled.div`
  width: 30px;
  height: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
`;

export const ModalDim = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
`;

export const ModalContainer = styled.div`
  background-color: white;
  border-radius: 10px;
  white-space: pre-line;
  text-align: center;

  p {
    padding: 20px;
  }
`;

export const ModalBtns = styled.div`
  display: flex;
`;

export const ModalBtn = styled.button`
  display: flex;
  -webkit-box-align: center;
  width: 100%;
  flex-direction: column;
  align-items: center;
  padding: 10px;
  border-top: 1px solid rgba(179, 179, 179, 0.39);

  span {
    font-size: 16px;
  }

  :first-child {
    border-right: 1px solid rgba(179, 179, 179, 0.39);
  }
`;
