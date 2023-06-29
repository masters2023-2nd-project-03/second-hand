import styled from 'styled-components';

export const IconWithCountStyle = styled.div`
  display: flex;
  ${({ theme }) => theme.typo.footnote}

  span {
    margin-left: 1px;
  }
`;
