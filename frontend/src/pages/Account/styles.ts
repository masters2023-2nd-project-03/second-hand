import styled, { css } from 'styled-components';
import Button from '../../components/Button';

interface ProfileImageButtonProps {
  profileImageUrl: string;
}

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;

  padding: 120px 0;
  height: 100%;
`;

export const ProfileImageButton = styled(Button)<ProfileImageButtonProps>`
  width: 80px;
  height: 80px;
  ${({ profileImageUrl }) => css`
    background-image: url(${profileImageUrl});
    background-position: center;
    background-size: cover;
  `}
`;
