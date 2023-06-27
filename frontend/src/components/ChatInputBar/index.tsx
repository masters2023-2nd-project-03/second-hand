import Icon from '../Icon';
import * as S from './styles';

const ChatInputBar = () => {
  return (
    <>
      <S.ChatInputContainer>
        <S.Menu>
          <S.Left>
            <S.ChatInput placeholder="내용을 입력하세요." />
          </S.Left>
          <S.Right>
            <Icon name="arrowUp" fill="white" width="17" height="17" />
          </S.Right>
        </S.Menu>
      </S.ChatInputContainer>
    </>
  );
};

export default ChatInputBar;
