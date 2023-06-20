import styled from 'styled-components';

interface NavBarContainerProps {
  type: string;
}

export const NavBarContainer = styled.div<NavBarContainerProps>`
  position: sticky;
  top: 0px;
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
  justify-content: center;
  align-items: center;
  width: 100%;
  padding: 16px 11px 16px 11px;
`;

export const PrevTitle = styled.div`
  width: 33%;
  display: flex;
  align-items: center;
  ${({ theme }) => theme.typo.body}
  ${({ theme }) => theme.color.neutralTextStrong}
`;
export const CenterTitle = styled.div`
  width: 33%;
  display: flex;
  justify-content: center;
  align-items: center;
  ${({ theme }) => theme.typo.headline}
  ${({ theme }) => theme.color.neutralTextStrong}
`;
export const RightTitle = styled.div`
  width: 33%;
  display: flex;
  justify-content: flex-end;
  align-items: center;
  ${({ theme }) => theme.typo.body}
  ${({ theme }) => theme.color.neutralTextStrong}
`;

export const PrevTitleContents = styled.div`
  display: flex;
  cursor: pointer;
`;

export const RightTitleContents = styled.div`
  display: flex;
  cursor: pointer;
`;
