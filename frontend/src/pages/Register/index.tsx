import { useEffect, useRef, useState, useMemo } from 'react';

import Icon from '../../components/Icon';
import NavBarTitle from '../../components/NavBarTitle';
import * as S from './styles';
import { useNavigate } from 'react-router-dom';
import { HOME, LOGIN } from '../../constants/routeUrl';
import { useAuthContext } from '../../context/Auth';
import { postJoin } from '../../api/member';

const Register = () => {
  const searchParams = new URLSearchParams(window.location.search);
  const nickname = searchParams.get('nickname') as string;
  const profileUrl = searchParams.get('profileUrl') as string;
  const oauthId = searchParams.get('oauthId') as string;

  const [nicknameInputValue, setNicknameValue] = useState<string>(nickname);
  const [profileImageFile, setProfileImageFile] = useState<File | null>(null);
  const { handleLogin } = useAuthContext();
  const profileImageInputRef = useRef<HTMLInputElement>(null);
  const navigate = useNavigate();

  const onProfileImageClick = () => {
    if (!profileImageInputRef.current)
      throw Error('profileImageRef in not assigned');
    profileImageInputRef.current.click();
  };

  const onProfileImageChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files && e.target.files.length > 0) {
      setProfileImageFile(e.target.files[0]);
    }
  };

  const onNicknameChange = (e: React.FormEvent<HTMLInputElement>) => {
    setNicknameValue(e.currentTarget.value);
  };

  const onSubmit = async () => {
    const res = await postJoin(
      nicknameInputValue,
      profileImageFile as File,
      oauthId,
    );

    if (res.data.success) {
      const { jwt } = res.data.data;
      handleLogin(jwt);
      navigate(HOME);
    } else {
      navigate(LOGIN);
    }
  };

  useEffect(() => {
    async function convertImageUrlToFile(imageUrl: string) {
      const response = await fetch(imageUrl);
      const blob = await response.blob();
      const file = new File([blob], 'profile.jpg', { type: 'image/jpeg' });
      setProfileImageFile(file);
    }

    convertImageUrlToFile(profileUrl);
  }, []);

  const profileImageUrl = useMemo(() => {
    return profileImageFile ? URL.createObjectURL(profileImageFile) : '';
  }, [profileImageFile]);

  return (
    <>
      <NavBarTitle
        type="low"
        prevTitle="닫기"
        centerTitle="회원가입"
        rightTitle="완료"
        rightTitleClick={onSubmit}
      />
      <S.Container>
        <S.ProfileImageButton
          circle
          onClick={onProfileImageClick}
          profileImageUrl={profileImageUrl}
        >
          <Icon name="camera" />
          <input
            type="file"
            accept="image/jpg, image/jpeg, image/png"
            multiple
            ref={profileImageInputRef}
            style={{ display: 'none' }}
            onChange={onProfileImageChange}
          />
        </S.ProfileImageButton>

        <S.ProfileIdInputBox>
          <S.ProfileIdLabel htmlFor="profile-id">닉네임</S.ProfileIdLabel>
          <S.ProfileIdInput
            id="profile-id"
            type="text"
            placeholder="닉네임을 입력하세요"
            value={nicknameInputValue}
            onChange={(e) => onNicknameChange(e)}
          />
        </S.ProfileIdInputBox>
      </S.Container>
    </>
  );
};
export default Register;
