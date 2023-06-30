import { useNavigate } from 'react-router-dom';
import { useEffect, useRef, useState } from 'react';
import { useParams } from 'react-router-dom';

import axios from 'axios';
import SockJS from 'sockjs-client';
import { Stomp, CompatClient } from '@stomp/stompjs';

import ChatRoomContents from '../../components/ChatRoomContents';
import ChatRoomItem from '../../components/ChatRoomItem';
import ChatInputBar from '../../components/ChatInputBar';
import NavBarTitle from '../../components/NavBarTitle';
import { ACCESS_TOKEN } from '../../constants/login';
import useAsync from '../../hooks/useAsync';
import { getSeller } from '../../api/product';

interface ChatHistoryProps {
  type: string;
  sender: string;
  message: string;
}

const ChatRoom = () => {
  const navigate = useNavigate();
  const client = useRef<CompatClient | null>(null);
  const curRoomId = sessionStorage.getItem('curRoomId') || undefined;
  const curProductsId = sessionStorage.getItem('curProductsId') || undefined;

  const accessToken = localStorage.getItem(ACCESS_TOKEN);

  // TODO : 판매자 번호 (추후 닉네임으로 받기)
  const sellerData = useAsync(() => getSeller(accessToken, curRoomId));
  const sellerId = sellerData?.data?.data.sellerId;

  const [chatHistoty, setChatHistory] = useState<ChatHistoryProps[] | null>(
    null,
  );

  // 과거 대화 내역 가죠오기
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          `http://52.79.159.39:8080/chat/room/history/${curRoomId}`,
          {
            headers: {
              Authorization: 'Bearer ' + accessToken,
              'Content-Type': 'application/json',
            },
          },
        );
        // 여기서 데이터 처리 로직을 추가하거나 상태를 업데이트할 수 있습니다.
        setChatHistory(response.data.data.messageHistory);
      } catch (error) {
        console.error('Error fetching data:', error);
        // 에러 처리 로직을 추가할 수 있습니다.
      }
    };

    fetchData(); // fetchData 함수를 호출하여 데이터를 가져옵니다.
  }, [curRoomId, accessToken]); // 빈 배열을 두 번째 매개변수로 전달하여 처음 마운트될 때만 실행되도록 합니다.

  // Todo : 채팅 내역 마운트와 동시에 대화 내용 엘리먼트 생성하기

  // 소켓 통신
  const [chatMessage, setChatMessage] = useState('');
  const [inputValue, setInputValue] = useState('');

  // 첫 소켓 연결
  const connectHandler = () => {
    const socket = new SockJS('http://52.79.159.39:8080/ws-stomp');

    client.current = Stomp.over(socket);

    client.current.connect(
      {
        Authorization: 'Bearer ' + accessToken,
        'Content-Type': 'application/json',
      },
      () => {
        client.current?.subscribe(
          `/sub/chat/room/${curRoomId}`,
          (message) => {
            setChatHistory((prevHistory) => {
              // 기존 대화 내역에 새로운 메시지 추가
              return prevHistory
                ? [...prevHistory, JSON.parse(message.body)]
                : null;
            });
            console.log(message.body);
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

  // 메시지 보내기
  const sendHandler = (inputValue: string) => {
    if (client.current && client.current.connected) {
      client.current.send(
        '/pub/chat/message',
        {
          Authorization: 'Bearer ' + accessToken,
          'Content-Type': 'application/json',
        },
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
      <ChatRoomContents chatHistory={chatHistoty} />
      <ChatInputBar onChange={setInputValue} />
    </>
  );
};

export default ChatRoom;
