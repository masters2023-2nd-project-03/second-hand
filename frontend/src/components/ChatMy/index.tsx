import * as S from './styles';

interface Message {
  message: string;
}

const ChatMy = ({ message }: Message) => {
  return (
    <>
      <S.ChatContainer>
        <S.Message>{message}</S.Message>
      </S.ChatContainer>
    </>
  );
};

export default ChatMy;
