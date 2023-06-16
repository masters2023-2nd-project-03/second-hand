import styled from 'styled-components';

interface NavBarContainerProps {
  type: string;
}

export const NavBarContainer = styled.div<NavBarContainerProps>`
  display: flex;
  align-items: flex-end;
  height: 100px;
  background-color: ${(props) => {
    if (props.type === 'low') {
      return 'rgba(249, 249, 249, 0)';
    } else if (props.type === 'medium') {
      return 'rgba(249, 249, 249, 0.8)';
    } else {
      return 'rgba(249, 249, 249, 1)';
    }
  }};
  border-bottom: solid 1px #b3b3b3;
`;

export const NavBarBody = styled.div`
  display: flex;
  justify-content: space-between;
  width: 100%;
  padding: 0 11px 11px 16px;
`;
