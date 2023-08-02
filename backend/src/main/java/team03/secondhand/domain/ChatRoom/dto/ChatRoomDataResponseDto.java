package team03.secondhand.domain.ChatRoom.dto;

import lombok.Getter;
import lombok.Setter;
import team03.secondhand.domain.ChatRoom.ChatRoom;
import team03.secondhand.domain.chat.Chat;
import team03.secondhand.domain.chat.dto.ChatDataResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChatRoomDataResponseDto {
    @Getter
    @Setter
    public static class Info {
        private Long roomId;
        private Long productId;
        private Long sellerId;
        private Long buyerId;

        private Info(Long roomId, Long productId, Long sellerId, Long buyerId) {
            this.roomId = roomId;
            this.productId = productId;
            this.sellerId = sellerId;
            this.buyerId = buyerId;
        }

        public static Info of(ChatRoom chatRoom) {
            return new Info(chatRoom.getChatroomId(),
                    chatRoom.getProduct().getProductId(),
                    chatRoom.getSeller().getMemberId(),
                    chatRoom.getBuyer().getMemberId());
        }
    }

    @Getter
    @Setter
    public static class ChatHistory {

        List<ChatDataResponseDto.Message> messageHistory = new ArrayList<>();

        public ChatHistory(List<Chat> messageHistory) {
            this.messageHistory = messageHistory.stream()
                    .map(chat -> new ChatDataResponseDto.Message(chat))
                    .collect(Collectors.toList());
        }

    }
}
