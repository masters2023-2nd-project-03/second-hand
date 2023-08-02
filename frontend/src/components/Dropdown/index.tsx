import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

import { ACCESS_TOKEN } from '../../constants/login';
import { useAuthContext } from '../../context/Auth';
import { patchMembersLocation } from '../../api/member';

import * as S from './styles';
import DropdownPanel from './DropdownPanel';
import Icon from '../Icon';
import { LOCATION } from '../../constants/routeUrl';

interface Location {
  locationId: number;
  locationDetails: string;
  locationShortening: string;
  mainLocationState: boolean;
}

interface DropdownProps {
  options: Location[];
  isSetLocationOption: boolean;
  isReverse: boolean;
}

const Dropdown = ({
  options,
  isSetLocationOption,
  isReverse,
}: DropdownProps) => {
  const navigate = useNavigate();
  const accessToken = localStorage.getItem(ACCESS_TOKEN);
  const { handleUpdateUserInfo } = useAuthContext();

  const [isOpen, setIsOpen] = useState(false);

  const mainLocation =
    options.find((locationInfo) => locationInfo.mainLocationState)
      ?.locationShortening || undefined;

  const handleFetchUserData = async (index: number) => {
    const mainLocationIndex = options.findIndex(
      (location) => location.mainLocationState === true,
    );
    const locationIdList = options.map((location) => location.locationId);

    if (mainLocationIndex !== index) locationIdList.reverse();

    await patchMembersLocation(accessToken, locationIdList);
    handleUpdateUserInfo();
    setIsOpen(false);
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
                mainLocationState={option.mainLocationState}
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
