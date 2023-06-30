import styled from 'styled-components';

export const ChatInputContainer = styled.div`
  display: flex;
  position: fixed;
  bottom: 0px;
  height: 80px;
  justify-content: space-between;
  width: 100%;
  background: #fafafa;
  ${({ theme }) => theme.color.neutralBorder}
  padding: 7px 16px 0 16px;
  flex-direction: column;
  z-index: 2;
`;

export const Menu = styled.div`
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 8px;
`;

export const Left = styled.div`
  flex-grow: 1;
`;

export const ChatInput = styled.input`
  font-size: 17px;
  line-height: 22px;
  font-weight: 400;
  background-color: white;
  width: 100%;

  padding: 4px 12px 4px 12px;
  border-radius: 18px;
  border: 1px solid #b3b3b3;
`;

export const Right = styled.button`
  width: 28px;
  height: 28px;
  border-radius: 50%;
  background-color: #ff9500;
  display: flex;
  align-items: center;
  justify-content: center;
`;
