import { useNavigate } from 'react-router-dom';

import * as S from './styles';

import { ACCESS_TOKEN } from '../../constants/login';
import { CHATROOM } from '../../constants/routeUrl';
import { getRoomsList, postNewChatRoom } from '../../api/chat';
import { formatNumber } from '../../utils/formatNumber';
import Button from '../Button';
import Icon from '../Icon';

// TODO : 함수를 실행하는 부모 컴포넌트 하나가 존재한다면 패치 컴포넌트? 에러 처리가 쉬워진다. (숙제)

interface DetailTapBarProps {
  price: number | null;
  curProductsId: string | undefined;
}

interface Room {
  roomId: string;
  productId: string;
  sellerId: number;
  buyerId: number;
}

const DetailTapBar = ({ price, curProductsId }: DetailTapBarProps) => {
  const navigate = useNavigate();
  const accessToken = localStorage.getItem(ACCESS_TOKEN);

  const handleChatClick = async () => {
    if (curProductsId) {
      try {
        // 방 생성 전에 있는 전체방 리스트 확인하기
        const roomsList = await getRoomsList(accessToken);
        // 현재 제품 ID와 일치하는 방이 있는지 확인
        const matchedRoom = roomsList.find(
          (room: Room) => String(room.productId) === curProductsId,
        );

        if (matchedRoom) {
          // 일치하는 방으로 이동
          enterChatRoom(matchedRoom.roomId);
        } else {
          // 일치하는 방이 없으면 새로운 방 생성
          await createChatRoom(curProductsId);
        }
      } catch (error) {
        console.error('방 생성 에러:', error);
      }
    }
  };

  // 방이 없다면 curProductsId와 accessToken으로 방 생성
  const createChatRoom = async (curProductsId: string) => {
    try {
      const createdRoomId = await postNewChatRoom(accessToken, curProductsId);
      // 생성된 방으로 이동
      enterChatRoom(createdRoomId);
    } catch (error) {
      console.error('방 생성 에러:', error);
    }
  };

  // 방으로 이동
  const enterChatRoom = (roomId: string) => {
    sessionStorage.setItem('curRoomId', roomId);
    if (curProductsId) sessionStorage.setItem('curProductsId', curProductsId);
    navigate(`${CHATROOM}/${roomId}`);
  };

  // TODO: 방이 있으면 "입장" 업으면 "채팅하기" 버튼 상태 변경 필요
  // TODO: 관심 제품이면 하트 색 주기
  // TODO: 판매자 확인하고 내 제품이면 채팅시작하기 없어야함

  return (
    <>
      <S.DetailTapBarContainer>
        <S.Menu>
          <div>
            <S.Left>
              <Icon name="heart" width="27" height="28" />
              {price !== null && <S.Price>{formatNumber(price)}원</S.Price>}
            </S.Left>
          </div>
          <S.Right>
            <Button active={!!curProductsId} onClick={handleChatClick}>
              채팅하기
            </Button>
          </S.Right>
        </S.Menu>
      </S.DetailTapBarContainer>
    </>
  );
};

export default DetailTapBar;
