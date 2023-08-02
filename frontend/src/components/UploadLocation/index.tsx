import * as S from './styles';

import Dropdown from '../Dropdown';
import Icon from '../Icon';
import { ACCESS_TOKEN } from '../../constants/login';
import useAsync from '../../hooks/useAsync';
import { getMembers } from '../../api/member';

const defaultLocation = [
  {
    locationDetails: '서울특별시 강남구 역삼1동',
    locationShortening: '역삼1동',
  },
];

const UploadLocation = () => {
  const accessToken = localStorage.getItem(ACCESS_TOKEN);
  const { data: userData } = useAsync(() => getMembers(accessToken));
  const userLocationDatas = userData?.data?.locationDatas || defaultLocation;

  return (
    <>
      <S.UploadLocationContainer>
        <S.Menu>
          <S.Left>
            <Icon name="slider" width="20" height="18" />
            <Dropdown
              options={userLocationDatas}
              isSetLocationOption={false}
              isReverse={true}
            />
          </S.Left>
          <S.Right>
            <Icon name="keyboard" width="20" height="18" />
          </S.Right>
        </S.Menu>
      </S.UploadLocationContainer>
    </>
  );
};

export default UploadLocation;
