import { NavLink } from 'react-router-dom';

import styled from 'styled-components';

export const TabBarContainer = styled.div`
  position: absolute;
  bottom: 0px;
  height: 80px;
  justify-content: space-between;
  width: 100%;
  background: #fafafa;
  ${({ theme }) => theme.color.neutralBorder}
  padding: 7px 16px 0 16px;
`;

export const TabBarMenu = styled.div`
  display: flex;
  justify-content: space-between;
  width: 100%;
`;

export const TabsList = styled.ul`
  display: flex;
  width: 100%;
  justify-content: space-between;
`;

export const StyledNavLink = styled(NavLink)`
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 7px;
  opacity: 0.6;
  ${({ theme }) => theme.color.neutralText}

  &.active {
    opacity: 1;
    ${({ theme }) => theme.color.neutralTextStrong}
  }
`;
