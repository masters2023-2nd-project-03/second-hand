import styled from 'styled-components';

export const ContentsContainer = styled.div`
  padding-top: 146px;
  padding-bottom: 80px;
  padding-left: 16px;
  padding-right: 16px;
  width: 100%;
  display: flex;
  flex-direction: column;
`;

export const Received = styled.div`
  width: 100%;
  display: flex;
  justify-content: flex-start;
  margin-bottom: 8px;
  z-index: 1;
`;

export const Sent = styled.div`
  width: 100%;
  display: flex;
  justify-content: flex-end;
  margin-bottom: 8px;
  z-index: 1;
`;
