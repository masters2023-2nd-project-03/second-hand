import styled from 'styled-components';

export const ChatInputContainer = styled.div`
  ${({ theme }) => theme.color.neutralBackgroundWeak}
  ${({ theme }) => theme.color.neutralBorder}
  display: flex;
  position: fixed;
  bottom: 0px;
  height: 80px;
  justify-content: space-between;
  width: 100%;
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
  ${({ theme }) => theme.typo.headline}
  ${({ theme }) => theme.color.neutralBackground}
  ${({ theme }) => theme.color.neutralBorder}
  width: 100%;
  padding: 4px 12px 4px 12px;
  border-radius: 18px;
`;

export const Right = styled.button`
  ${({ theme }) => theme.color.accentBackgroundPrimary}
  width: 28px;
  height: 28px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
`;
