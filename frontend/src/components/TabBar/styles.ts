import styled, { css } from 'styled-components';

type TabBarProps = {
  active: boolean;
};

export const tabBarContainer = styled.div`
  position: absolute;
  bottom: 0px;
  height: 80px;
  justify-content: space-between;
  width: 100%;
  background: #fafafa;
  border-top: 1px solid rgba(179, 179, 179, 0.39);
  padding: 7px 16px 0 16px;
`;

export const tabBarMenu = styled.div`
  display: flex;
  justify-content: space-between;
  width: 100%;
`;

export const Ul = styled.ul`
  display: flex;
  width: 100%;
  justify-content: space-between;
`;

export const Li = styled.li<TabBarProps>`
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 7px;
  opacity: 0.6;
  ${(props) =>
    props.active &&
    css`
      opacity: 1;
    `}
`;
