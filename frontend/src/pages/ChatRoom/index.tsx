import { useNavigate } from 'react-router-dom';
import { useEffect, useRef, useState } from 'react';

import SockJS from 'sockjs-client';
import { Stomp, CompatClient } from '@stomp/stompjs';

import ChatRoomContents from '../../components/ChatRoomContents';
import ChatRoomItem from '../../components/ChatRoomItem';
import ChatInputBar from '../../components/ChatInputBar';
import NavBarTitle from '../../components/NavBarTitle';
import { BASE_URL } from '../../constants/api';
import { ACCESS_TOKEN } from '../../constants/login';
import useAsync from '../../hooks/useAsync';
import { getSeller } from '../../api/member';
import { getChatDetails } from '../../api/chat';

interface ChatHistoryProps {
  type: string;
  sender: string;
  message: string;
}

const ChatRoom = () => {
  const navigate = useNavigate();
  const accessToken = localStorage.getItem(ACCESS_TOKEN);

  // Stomp의 CompatClient 객체를 참조하는 객체 (리렌더링에도 유지를 위해 useRef 사용)
  // Stomp라이브러리와 소켓 연결을 수행하는 cliet객체에 접근할 수 있게 해준다.
  const client = useRef<CompatClient | null>(null);

  const curRoomId = sessionStorage.getItem('curRoomId') || undefined;
  const curProductsId = sessionStorage.getItem('curProductsId') || undefined;

  // TODO : 판매자 번호 (추후 닉네임으로 받기)
  const sellerData = useAsync(() => getSeller(accessToken, curRoomId));
  const sellerId = sellerData?.data?.data.sellerId;

  const [chatHistory, setChatHistory] = useState<ChatHistoryProps[] | null>(
    null,
  );
  const [inputValue, setInputValue] = useState('');

  // 채팅 내역 조회하고 불러오기
  const checkChatDetails = async () => {
    try {
      const chatDetails = await getChatDetails(accessToken, curRoomId);
      setChatHistory(chatDetails);
    } catch (error) {
      console.error('채팅 내역 불러오기 에러:', error);
    }
  };

  useEffect(() => {
    checkChatDetails();
  }, [accessToken, curRoomId]);

  // 소켓 연결
  const connectHandler = () => {
    // SockJS 클라이언트 객체를 생성하고, 웹 소켓을 연결한다.
    // ws-stomp는 서버의 Endpoint 경로로, 웹 소켓 통신을 위한 특정 경로를 의미한다.
    const socket = new SockJS(`${BASE_URL}/ws-stomp`);

    // SockJS 클라이언트 객체 socket를 STOMP 프로토콜로 오버랩하여 client.current에 할당
    client.current = Stomp.over(socket);
    // 클라이언트 객체를 서버와 연결
    client.current.connect(
      {
        Authorization: 'Bearer ' + accessToken,
        'Content-Type': 'application/json',
      },
      () => {
        // 연결 성공 시 해당 방을 구독하면 서버로부터 새로운 매시지를 수신 한다.
        client.current?.subscribe(
          `/sub/chat/room/${curRoomId}`,
          (message) => {
            // 기존 대화 내역에 새로운 메시지 추가
            setChatHistory((prevHistory) => {
              return prevHistory
                ? [...prevHistory, JSON.parse(message.body)]
                : null;
            });
          },
          {
            Authorization: 'Bearer ' + accessToken,
            'Content-Type': 'application/json',
          },
        );
      },
    );
  };

  useEffect(() => {
    connectHandler();
  }, [accessToken, curRoomId]);

  // 소켓을 통해 메시지를 전송
  const sendHandler = (inputValue: string) => {
    // client.current가 존재하고 연결되었다면 메시지 전송
    if (client.current && client.current.connected) {
      client.current.send(
        '/pub/chat/message',
        {
          Authorization: 'Bearer ' + accessToken,
          'Content-Type': 'application/json',
        },
        // JSON 형식으로 전송한다
        JSON.stringify({
          type: 'TALK',
          roomId: curRoomId,
          message: inputValue,
        }),
      );
    }
  };

  const handleBackIconClick = () => {
    sessionStorage.clear();
    navigate(-1);
  };

  useEffect(() => {
    sendHandler(inputValue);
  }, [inputValue]);

  return (
    <>
      <NavBarTitle
        type="high"
        backIcon
        prevTitle="뒤로"
        centerTitle={sellerId}
        moreIcon
        preTitleClick={handleBackIconClick}
      />
      <ChatRoomItem curProductsId={curProductsId} />
      <ChatRoomContents chatHistory={chatHistory} />
      <ChatInputBar onChange={setInputValue} />
    </>
  );
};

export default ChatRoom;
