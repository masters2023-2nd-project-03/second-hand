import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

import * as S from './styles';

import DropdownPanel from './DropdownPanel';
import Icon from '../Icon';
import { ACCESS_TOKEN } from '../../constants/login';
import { patchMainLocation } from '../../api/member';
import { LOCATION } from '../../constants/routeUrl';

interface Location {
  locationId: string;
  locationDetails: string;
  locationShortening: string;
  isMainLocation: boolean;
}

interface DropdownProps {
  options: Location[];
  isSetLocationOption: boolean;
  isReverse: boolean;
  fetchUserData?: () => void;
}

const Dropdown = ({
  options,
  isSetLocationOption,
  isReverse,
  fetchUserData,
}: DropdownProps) => {
  const navigate = useNavigate();
  const accessToken = localStorage.getItem(ACCESS_TOKEN);
  const [isOpen, setIsOpen] = useState(false);

  const mainLocation =
    options.find((locationInfo) => locationInfo.isMainLocation)
      ?.locationShortening || undefined;

  const handleFetchUserData = async (index: number) => {
    console.log('클릭한 동네 배열 인덱스', index);

    // TODO : 유저 정보 변경하는 API 필요
    await patchMainLocation(accessToken, index);

    if (fetchUserData) {
      fetchUserData();
    }
  };

  const toggleDropdown = () => {
    setIsOpen((prevIsOpen) => !prevIsOpen);
  };

  const handleChangeLocationClick = () => {
    setIsOpen(false);
    navigate(LOCATION);
  };

  return (
    <>
      {isOpen === true && <S.DropdownWrapper onClick={toggleDropdown} />}
      <S.DropdownContainer>
        <S.DropdownHeader onClick={toggleDropdown}>
          <S.SelectedOption>{mainLocation}</S.SelectedOption>
          {isReverse === false && <Icon name={'chevronDown'} width="17" />}
        </S.DropdownHeader>
        {isOpen && (
          <S.PanelContainer isReverse={isReverse}>
            {options.map((option, index) => (
              <DropdownPanel
                key={index}
                option={option.locationShortening}
                isMainLocation={option.isMainLocation}
                onClickNonOption={
                  options.length === 1
                    ? undefined
                    : () => handleFetchUserData(index)
                }
              />
            ))}
            {isSetLocationOption && (
              <DropdownPanel
                key={2}
                option={'내 동네 변경하기'}
                onClickOption={handleChangeLocationClick}
                isLastPanel={true}
              />
            )}
          </S.PanelContainer>
        )}
      </S.DropdownContainer>
    </>
  );
};

export default Dropdown;
