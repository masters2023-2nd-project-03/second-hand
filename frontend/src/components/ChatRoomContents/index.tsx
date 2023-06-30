import ChatOtherPerson from '../ChatOtherPerson';
import ChatMy from '../ChatMy';
import * as S from './styles';
import { ACCESS_TOKEN } from '../../constants/login';
import useAsync from '../../hooks/useAsync';
import { getMembers } from '../../api/product';

interface ChatHistoryProps {
  type: string;
  sender: string;
  message: string;
}

interface ChatRoomContentsProps {
  chatHistory: ChatHistoryProps[] | null;
}

const ChatRoomContents = ({ chatHistory }: ChatRoomContentsProps) => {
  const accessToken = localStorage.getItem(ACCESS_TOKEN);
  const { data: userData } = useAsync(() => getMembers(accessToken));
  console.log('채팅내역 확인', chatHistory);

  return (
    <>
      <S.ContentsContainer>
        {chatHistory?.map((chat, index) => (
          <li key={index}>
            {chat.sender === userData?.data.nickname ? (
              <S.Sent>
                <ChatMy message={chat.message} />
              </S.Sent>
            ) : (
              <S.Received>
                <ChatOtherPerson message={chat.message} />
              </S.Received>
            )}
          </li>
        ))}
      </S.ContentsContainer>
    </>
  );
};

export default ChatRoomContents;
