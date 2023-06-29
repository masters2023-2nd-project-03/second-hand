package team03.secondhand.domain.ChatRoom.dto;

import lombok.Getter;
import lombok.Setter;

public class ChatRoomDataRequestDto {
    @Getter
    @Setter
    public static class create {
        private Long productId;

    }
}
