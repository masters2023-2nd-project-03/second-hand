import { ACCESS_TOKEN } from '../../constants/login';
import Dropdown from '../Dropdown';
import Icon from '../Icon';
import * as S from './styles';
import useAsync from '../../hooks/useAsync';
import { getMembers } from '../../api/product';

const UploadLocation = () => {
  // 사용자 정보 가져오기
  const accessToken = localStorage.getItem(ACCESS_TOKEN);
  const { data } = useAsync(() => getMembers(accessToken));
  console.log(data);

  return (
    <>
      <S.UploadLocationContainer>
        <S.Menu>
          <S.Left>
            <Icon name="slider" width="20" height="18" />
            <Dropdown
              options={['역삼1동', '역삼2동']}
              useSetting={false}
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
