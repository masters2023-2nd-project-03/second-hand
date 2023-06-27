import * as S from './styles';

const sampleMassage = '네 안녕하세요. 가격 흥정 안되요.';

const ChatMy = () => {
  return (
    <>
      <S.ChatContainer>
        <S.Message>{sampleMassage}</S.Message>
      </S.ChatContainer>
    </>
  );
};

export default ChatMy;
