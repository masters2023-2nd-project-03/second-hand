package team03.secondhand.domain.ChatRoom;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team03.secondhand.domain.ChatRoom.dto.ChatRoomDataRequestDto;
import team03.secondhand.domain.ChatRoom.dto.ChatRoomDataResponseDto;
import team03.secondhand.domain.chat.Chat;
import team03.secondhand.domain.chat.ChatRepository;
import team03.secondhand.domain.member.Member;
import team03.secondhand.domain.member.MemberRepository;
import team03.secondhand.domain.product.Product;
import team03.secondhand.domain.product.ProductRepository;
import team03.secondhand.error.ChatRoomError;
import team03.secondhand.error.MemberError;
import team03.secondhand.error.ProductError;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChatRoomService {

    private final ChatRepository chatRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    public List<ChatRoomDataResponseDto.Info> findAllRoomByMemberId(Long memberId) {
        List<ChatRoom> ChatRooms = chatRoomRepository.findByMemberId(memberId);
        return ChatRooms.stream()
                .map(chatRoom -> ChatRoomDataResponseDto.Info.of(chatRoom))
                .collect(Collectors.toList());
    }

    public ChatRoomDataResponseDto.Info findRoomByRoomId(Long roomId) {
        ChatRoom chatRoom = chatRoomRepository.findByChatroomId(roomId)
                .orElseThrow(() -> new ChatRoomError.NotFoundChatRoom());
        return ChatRoomDataResponseDto.Info.of(chatRoom);
    }

    public ChatRoomDataResponseDto.Info createRoom(Long memberId, ChatRoomDataRequestDto.create createRequest) {
        Product product = productRepository.findProductByProductId(createRequest.getProductId())
                .orElseThrow(() -> new ProductError.NotFoundProduct());
        Member seller = product.getMember();
        Member buyer = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new MemberError.RequireRegistration());

        if (chatRoomRepository.findByMemberIdAndProductId(memberId, createRequest.getProductId()).isPresent()) {
            throw new ChatRoomError.ConflictChatRoom();
        }

        // TODO : 이미 채팅방이 생성되 있을 수도 있음
        ChatRoom chatRoom = ChatRoom.builder()
                .seller(seller)
                .buyer(buyer)
                .product(product)
                .build();

        chatRoomRepository.save(chatRoom);

        return ChatRoomDataResponseDto.Info.of(chatRoom);
    }

    public ChatRoomDataResponseDto.ChatHistory getChatHistory(Long roomId) {
        List<Chat> chatHistory = chatRepository.getChatByRoomId(roomId);
        return new ChatRoomDataResponseDto.ChatHistory(chatHistory);
    }

}
