import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

import useAsync from '../../hooks/useAsync';
import { useAuthContext } from '../../context/Auth';
import { ACCESS_TOKEN } from '../../constants/login';
import { getLocation } from '../../api/location';
import { patchMembersLocation } from '../../api/member';

import * as S from './styles';
import NavBarTitle from '../../components/NavBarTitle';
import { LOCATION } from '../../constants/routeUrl';
import { SearchBar } from '../../components/SearchBar';

interface Location {
  locationId: number;
  locationDetails: string;
  locationShortening: string;
  isMainLocation: boolean;
}

export const LocationSetPage = () => {
  const navigate = useNavigate();
  const accessToken = localStorage.getItem(ACCESS_TOKEN);
  const loggedInUserDate = useAuthContext();
  const curLocationsData = loggedInUserDate.userInfo.locationDatas;
  const { handleUpdateUserInfo } = useAuthContext();

  const { data } = useAsync(getLocation);
  const locationList = data?.data;

  const [searchTerm, setSearchTerm] = useState('');
  const [searchResults, setSearchResults] = useState<Location[]>([]);

  const handleAddLocation = async (event: React.MouseEvent<HTMLElement>) => {
    const targetElement = event.target as Element;
    const closestLiElement = targetElement.closest('li');

    if (closestLiElement) {
      const clickedLocationId = Number(targetElement.getAttribute('data-key'));
      const locationIdList = [
        ...curLocationsData.map((location) => location.locationId),
        clickedLocationId,
      ];

      await patchMembersLocation(accessToken, locationIdList);
      handleUpdateUserInfo();
      navigate(LOCATION);
    }
  };

  useEffect(() => {
    setSearchResults(locationList);
  }, [locationList]);

  useEffect(() => {
    const results = locationList?.filter((neighborhood: Location) =>
      neighborhood.locationShortening.startsWith(searchTerm),
    );
    setSearchResults(results);
  }, [searchTerm]);

  const handelBackClick = () => {
    navigate(LOCATION);
  };

  return (
    <>
      <NavBarTitle
        type="high"
        prevTitle="닫기"
        preTitleClick={handelBackClick}
      />
      <S.Main>
        <SearchBar searchTerm={searchTerm} setSearchTerm={setSearchTerm} />
        <ul onClick={(event) => handleAddLocation(event)}>
          {searchResults?.map((location: Location) => (
            <S.location
              key={location.locationId}
              data-key={location.locationId}
            >
              <span>{location.locationDetails}</span>
            </S.location>
          ))}
        </ul>
      </S.Main>
    </>
  );
};
