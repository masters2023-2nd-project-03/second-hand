package team03.secondhand.domain.chat.dto;

import lombok.Getter;
import lombok.Setter;

public class ChatDataRequestDto {

    @Getter
    @Setter
    public static class Message {
        public enum MessageType {
            ENTER, TALK
        }
        private MessageType type;
        private Long roomId;
        private String message;
    }
}
