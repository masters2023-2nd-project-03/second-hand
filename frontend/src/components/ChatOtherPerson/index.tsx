import * as S from './styles';

const sampleMassage = '안녕하세요. 시저 사고 싶어요. 네고 가능하세요?';

const ChatOtherPerson = () => {
  return (
    <>
      <S.ChatContainer>
        <S.Message>{sampleMassage}</S.Message>
      </S.ChatContainer>
    </>
  );
};

export default ChatOtherPerson;
