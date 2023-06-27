import ChatOtherPerson from '../ChatOtherPerson';
import ChatMy from '../ChatMy';
import * as S from './styles';

const ChatRoomContents = () => {
  return (
    <>
      <S.ContentsContainer>
        <S.Received>
          <ChatOtherPerson />
        </S.Received>
        <S.Sent>
          <ChatMy />
        </S.Sent>
      </S.ContentsContainer>
    </>
  );
};

export default ChatRoomContents;
