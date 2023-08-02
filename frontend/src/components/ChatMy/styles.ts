import styled from 'styled-components';

export const ChatContainer = styled.div`
  ${({ theme }) => theme.color.accentBackgroundPrimary}
  width: auto;
  height: auto;
  max-width: 272px;
  padding: 8px;
  border-radius: 15px;
  border-bottom-right-radius: 0px;
`;

export const Message = styled.span`
  ${({ theme }) => theme.typo.content}
  ${({ theme }) => theme.color.accentText}
`;
