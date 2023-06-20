import styled from 'styled-components';

interface NavBarContainerProps {
  type: string;
}

export const NavBarContainer = styled.div<NavBarContainerProps>`
  position: fixed;
  top: 0px;
  height: 50px;
  left: 0;
  width: 100%;
  padding: 16px;
  align-items: center;
  display: flex;
  border-bottom: solid 1px #b3b3b3;
  background-color: ${(props) => {
    if (props.type === 'low') {
      return 'rgba(249, 249, 249, 0)';
    } else if (props.type === 'medium') {
      return 'rgba(249, 249, 249, 0.8)';
    } else {
      return 'rgba(249, 249, 249, 1)';
    }
  }};
`;

export const NavBarBody = styled.div`
  display: flex;
  justify-content: space-between;
  width: 100%;
`;

export const ClinkElement = styled.div`
  cursor: pointer;
  align-items: center;
  display: flex;
`;
