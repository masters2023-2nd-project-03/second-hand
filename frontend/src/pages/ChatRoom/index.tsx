import ChatRoomContents from '../../components/ChatRoomContents';
import ChatRoomItem from '../../components/ChatRoomItem';
import ChatInputBar from '../../components/ChatInputBar';
import NavBarTitle from '../../components/NavBarTitle';

const ChatRoom = () => {
  return (
    <>
      <NavBarTitle
        type="high"
        backIcon
        prevTitle="뒤로"
        centerTitle="판매자"
        moreIcon
      />
      <ChatRoomItem />
      <ChatRoomContents />
      <ChatInputBar />
    </>
  );
};

export default ChatRoom;
