import ChatRoomItem from '../../components/ChatRoomItem';
import NavBarTitle from '../../components/NavBarTitle';
import * as S from './styles';

const ChatRoom = () => {
  return (
    <>
      <NavBarTitle
        type="low"
        backIcon
        prevTitle="뒤로"
        centerTitle="판매자"
        moreIcon
      />
      <S.Main>
        <ChatRoomItem />
        <div className="ChatContents">채팅내용</div>
      </S.Main>
      <div className="ChatInputBar">입력</div>
    </>
  );
};

export default ChatRoom;
