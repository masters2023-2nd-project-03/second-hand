package team03.secondhand.domain.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team03.secondhand.domain.ChatRoom.ChatRoom;
import team03.secondhand.domain.ChatRoom.ChatRoomRepository;
import team03.secondhand.domain.chat.dto.ChatDataRequestDto;
import team03.secondhand.domain.chat.dto.ChatDataResponseDto;
import team03.secondhand.domain.member.Member;
import team03.secondhand.domain.member.MemberRepository;
import team03.secondhand.error.ChatRoomError;
import team03.secondhand.error.MemberError;

@RequiredArgsConstructor
@Service
public class ChatService {

    private final SimpMessageSendingOperations messagingTemplate;
    private final ChatRepository chatRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void sendMessage(Long memberId, ChatDataRequestDto.Message message) {
        ChatRoom chatRoom = chatRoomRepository.findByChatroomId(message.getRoomId())
                .orElseThrow(() -> new ChatRoomError.NotFoundChatRoom());
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new MemberError.RequireRegistration());

        Chat chat = Chat.builder()
                .sender(member)
                .chatRoom(chatRoom)
                .content(message.getMessage())
                .build();

        chatRepository.save(chat);

        ChatDataResponseDto.Message sendMessage = new ChatDataResponseDto.Message();
        sendMessage.setMessage(message.getMessage());
        sendMessage.setSender(member.getNickname());
        sendMessage.setType(ChatDataResponseDto.Message.MessageType.TALK);

        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), sendMessage);
    }

}
