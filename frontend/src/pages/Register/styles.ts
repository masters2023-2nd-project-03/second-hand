import styled, { css } from 'styled-components';
import Button from '../../components/Button';

interface ProfileImageButtonProps {
  profileImageUrl: string;
}

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;

  padding: 80px 16px;
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

export const ProfileIdInputBox = styled.div`
  display: flex;
  align-items: center;

  padding: 11px 0;
`;

export const ProfileIdLabel = styled.label`
  ${({ theme }) => theme.typo.headline}
`;

export const ProfileIdInput = styled.input`
  ${({ theme }) => theme.typo.headline}
  font-weight: 400;
  margin-left: 53px;
`;

export const ProfileLocationButton = styled(Button)`
  margin-top: 40px;
`;
