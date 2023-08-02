import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

import { useAuthContext } from '../../context/Auth';
import { ACCESS_TOKEN } from '../../constants/login';
import { patchMembersLocation } from '../../api/member';

import * as S from './styles';
import NavBarTitle from '../../components/NavBarTitle';
import Button from '../../components/Button';
import Icon from '../../components/Icon';
import { HOME, LOCATION_SET } from '../../constants/routeUrl';

interface Location {
  locationId: number;
  locationDetails: string;
  locationShortening: string;
  mainLocationState: boolean;
}

export const LocationPage = () => {
  const navigate = useNavigate();
  const accessToken = localStorage.getItem(ACCESS_TOKEN);
  const loggedInUserData = useAuthContext();
  const curLocationsData = loggedInUserData.userInfo.locationDatas;
  const { handleUpdateUserInfo } = useAuthContext();

  const [isModalOpen, setModalOpen] = useState(false);
  const [selectedLocation, setSelectedLocation] = useState<Location | null>(
    null,
  );
  const [modalMessage, setModalMessage] = useState<string>('');

  const handleFetchUserData = async (index: number) => {
    const mainLocationIndex = curLocationsData.findIndex(
      (location) => location.mainLocationState === true,
    );
    const locationIdList = curLocationsData.map(
      (location) => location.locationId,
    );
    if (mainLocationIndex !== index) locationIdList.reverse();
    await patchMembersLocation(accessToken, locationIdList);
    handleUpdateUserInfo();
    navigate(HOME);
  };

  const handleDelLocation = async () => {
    const locationIdList = curLocationsData.reduce((acc, location) => {
      if (selectedLocation?.locationId !== location.locationId) {
        acc.push(location.locationId);
      }
      return acc;
    }, []);

    await patchMembersLocation(accessToken, locationIdList);
    handleUpdateUserInfo();

    setModalOpen(false);
  };

  const handleAddClick = () => {
    navigate(LOCATION_SET);
  };

  const handleOpenModal = (event: React.MouseEvent, location: Location) => {
    event.stopPropagation();
    setSelectedLocation(location);

    if (curLocationsData.length === 1) {
      setModalMessage(
        `동네는 최소 1개 이상 선택해야해요.
        새로운 동네를 등록한 후, 삭제해주세요.`,
      );
    } else {
      setModalMessage(
        `정말로 '${location.locationShortening}'을(를) 삭제하시겠습니까?`,
      );
    }
    setModalOpen(true);
  };

  const handleCloseModal = () => {
    setModalOpen(false);
    setSelectedLocation(null);
  };

  const handelBackClick = () => {
    navigate(HOME);
  };

  return (
    <>
      <NavBarTitle
        type="high"
        prevTitle="닫기"
        preTitleClick={handelBackClick}
        centerTitle="동네 설정"
      />
      <S.Main>
        <S.LocationContainer>
          <S.Signboard>
            <span>지역은 최소 1개,</span>
            <span>최대 2개까지 설정 가능해요.</span>
          </S.Signboard>
          <S.BtnContainer>
            {curLocationsData.map((location, index) => (
              <Button
                key={location.locationId}
                spaceBetween
                fullWidth
                active={location.mainLocationState}
                onClick={() => handleFetchUserData(index)}
              >
                <span>{location.locationShortening}</span>
                <S.DeleteButton
                  onClick={(event) => handleOpenModal(event, location)}
                >
                  <Icon
                    name="x"
                    width="30"
                    height="20"
                    fill={location.mainLocationState ? 'white' : 'black'}
                  />
                </S.DeleteButton>
              </Button>
            ))}
            {curLocationsData.length === 1 && (
              <Button onClick={handleAddClick} fullWidth>
                <Icon name="symbol" width="13" height="20" fill="black" />
                <span>동네 추가</span>
              </Button>
            )}
          </S.BtnContainer>
        </S.LocationContainer>
        {isModalOpen && (
          <S.ModalDim>
            <S.ModalContainer>
              <p>{modalMessage}</p>
              <S.ModalBtns>
                <S.ModalBtn onClick={handleCloseModal}>
                  <span>취소</span>
                </S.ModalBtn>
                {modalMessage.includes('삭제하시겠습니까') && (
                  <S.ModalBtn onClick={handleDelLocation}>
                    <span>삭제</span>
                  </S.ModalBtn>
                )}
              </S.ModalBtns>
            </S.ModalContainer>
          </S.ModalDim>
        )}
      </S.Main>
    </>
  );
};
