import styled from 'styled-components';

export const ChatContainer = styled.div`
  background-color: #d9d9d9;
  width: auto;
  height: auto;
  max-width: 272px;
  padding: 8px;
  border-radius: 15px;
  position: relative;
  border-top-left-radius: 0px;
`;

export const Message = styled.span`
  ${({ theme }) => theme.typo.content}
  ${({ theme }) => theme.color.accentTextWeak}
`;
