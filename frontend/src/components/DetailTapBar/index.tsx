import { useNavigate } from 'react-router-dom';
import axios from 'axios';

import { ACCESS_TOKEN } from '../../constants/login';
import { CHATROOM } from '../../constants/routeUrl';
import formatNumber from '../../utils/formatNumber';
import Button from '../Button';
import Icon from '../Icon';
import * as S from './styles';

import useAsync from '../../hooks/useAsync';
import { getMembers } from '../../api/product';
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

  const handleChatClick = async () => {
    if (curProductsId) {
      const accessToken = localStorage.getItem(ACCESS_TOKEN);

      // 방 생성 전에 있는 방 확인하기
      const checkChatRoom = async () => {
        try {
          const response = await axios.get(
            `http://52.79.159.39:8080/chat/rooms`,
            {
              headers: {
                Authorization: 'Bearer ' + accessToken,
                'Content-Type': 'application/json',
              },
            },
          );
          const roomsInfo = response.data.data;
          console.log(curProductsId);
          console.log(roomsInfo[0].productId);

          // 현재 제품 ID와 일치하는 방이 있는지 확인
          const matchedRoom = roomsInfo.find(
            (room: Room) => String(room.productId) === curProductsId,
          );

          console.log(matchedRoom);

          if (matchedRoom) {
            // 일치하는 방으로 이동
            enterChatRoom(matchedRoom.roomId);
          } else {
            // 일치하는 방이 없으면 새로운 방 생성
            createChatRoom(curProductsId);
          }
        } catch (error) {
          console.error('방 생성 에러:', error);
        }
      };

      // 1. productsId와 accessToken으로 방 생성
      const createChatRoom = async (productId: string) => {
        try {
          const response = await axios.post(
            `http://52.79.159.39:8080/chat/room/create`,
            {
              productId: productId,
            },
            {
              headers: {
                Authorization: 'Bearer ' + accessToken,
                'Content-Type': 'application/json',
              },
            },
          );
          const roomId = response.data.data.roomId;
          sessionStorage.setItem('curRoomId', roomId);
          sessionStorage.setItem('curProductsId', curProductsId);
          console.log('방 생성 완료 - roomId:', roomId);
          console.log('제품번호 - roomId:', curProductsId);

          // 해당 방으로 이동
          enterChatRoom(roomId);
        } catch (error) {
          console.error('방 생성 에러:', error);
        }
      };

      // 방으로 이동
      const enterChatRoom = (roomId: string) => {
        sessionStorage.setItem('curRoomId', roomId);
        sessionStorage.setItem('curProductsId', curProductsId);
        navigate(`${CHATROOM}/${roomId}`);
      };

      await checkChatRoom();
    }

    console.log('채팅방 생성 - productId:', curProductsId);
  };

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
            <Button active onClick={handleChatClick}>
              채팅하기
            </Button>
          </S.Right>
        </S.Menu>
      </S.DetailTapBarContainer>
    </>
  );
};

export default DetailTapBar;
