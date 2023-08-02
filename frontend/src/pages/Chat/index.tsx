import { useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';

import { CHATROOM } from '../../constants/routeUrl';
import { ACCESS_TOKEN } from '../../constants/login';
import { getRoomsList } from '../../api/chat';

// TODO : 채팅 페이지 꾸미기
// TODO : 채팅방 목록 받아오기에서 판매자 이름도 필요함

interface Room {
  roomId: string;
  productId: string;
  sellerId: string;
  buyerId: string;
}

const ChatPage = () => {
  const accessToken = localStorage.getItem(ACCESS_TOKEN);
  const navigate = useNavigate();

  const [myChatRoomList, setMyChatRoomList] = useState<Room[]>([]);

  const checkChatRooms = async () => {
    try {
      const roomsList = await getRoomsList(accessToken);
      setMyChatRoomList(roomsList);
    } catch (error) {
      console.error('방 리스트 불러오기 에러:', error);
    }
  };

  const enterChatRoom = (roomId: string, productId: string) => {
    sessionStorage.setItem('curRoomId', roomId);
    sessionStorage.setItem('curProductsId', productId);
    navigate(`${CHATROOM}/${roomId}`);
  };

  useEffect(() => {
    checkChatRooms();
  }, []);

  return (
    <>
      <h1>채팅 이당</h1>
      <ul>
        {myChatRoomList.map((room) => (
          <li
            key={room.roomId}
            onClick={() => enterChatRoom(room.roomId, room.productId)}
          >
            제품 아이디 : {room.productId} 방 번호 : {room.roomId}
          </li>
        ))}
      </ul>
    </>
  );
};

export default ChatPage;
