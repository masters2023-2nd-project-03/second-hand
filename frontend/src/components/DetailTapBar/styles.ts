import styled from 'styled-components';

export const DetailTapBarContainer = styled.div`
  ${({ theme }) => theme.color.neutralBackgroundWeak}
  ${({ theme }) => theme.color.neutralBorder}
  display: flex;
  position: fixed;
  bottom: 0px;
  height: 80px;
  justify-content: space-between;
  width: 100%;
  padding: 7px 16px 0 16px;
`;

export const Menu = styled.div`
  width: 100%;
  display: flex;
  justify-content: space-between;
`;

export const Left = styled.div`
  ${({ theme }) => theme.typo.footnote}
  display: flex;
  align-items: center;
  gap: 5px;
`;

export const Price = styled.span`
  ${({ theme }) => theme.typo.footnote}
`;

export const Right = styled.div``;
