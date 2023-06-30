import { useNavigate } from 'react-router-dom';
import { useEffect, useRef, useState } from 'react';

import axios from 'axios';
import SockJS from 'sockjs-client';
import { Stomp, CompatClient } from '@stomp/stompjs';

import { CHATROOM } from '../../constants/routeUrl';

import ChatRoomContents from '../../components/ChatRoomContents';
import ChatRoomItem from '../../components/ChatRoomItem';
import ChatInputBar from '../../components/ChatInputBar';
import NavBarTitle from '../../components/NavBarTitle';
import { ACCESS_TOKEN } from '../../constants/login';
import useAsync from '../../hooks/useAsync';
import { getSeller } from '../../api/product';

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

  const checkChatRoom = async () => {
    try {
      const response = await axios.get(`http://52.79.159.39:8080/chat/rooms`, {
        headers: {
          Authorization: 'Bearer ' + accessToken,
          'Content-Type': 'application/json',
        },
      });
      const roomsInfo = response.data.data;
      setMyChatRoomList(roomsInfo);
    } catch (error) {
      console.error('방 생성 에러:', error);
    }
  };

  useEffect(() => {
    checkChatRoom();
  }, []);

  const enterChatRoom = (roomId: string, productId: string) => {
    sessionStorage.setItem('curRoomId', roomId);
    sessionStorage.setItem('curProductsId', productId);
    navigate(`${CHATROOM}/${roomId}`);
  };

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
