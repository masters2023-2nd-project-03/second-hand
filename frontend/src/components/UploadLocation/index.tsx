import { useEffect } from 'react';

import axios from 'axios';

import Dropdown from '../Dropdown';
import Icon from '../Icon';
import * as S from './styles';
import { BASE_URL } from '../../constants/api';

const UploadLocation = () => {
  useEffect(() => {
    const fetchData = async () => {
      try {
        const token =
          'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjg3MTAyMjc1LCJleHAiOjE2ODg5MDIyNzV9.AhaCUeK_M_Ph3dVTf4VCceB-Wk2AWp1ukYdP5G4VpCU';

        const response = await axios.get(`${BASE_URL}/api/members`, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });

        if (response.status === 200) {
          console.log('GET 요청이 성공적으로 완료되었습니다.');
        } else {
          console.log(
            'GET 요청이 실패하였습니다. 응답 상태 코드:',
            response.status,
          );
        }
      } catch (error) {
        const err = error as Error;
        console.log('GET 요청 중 오류가 발생하였습니다:', err.message);
      }
    };

    fetchData();
  }, []);

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
