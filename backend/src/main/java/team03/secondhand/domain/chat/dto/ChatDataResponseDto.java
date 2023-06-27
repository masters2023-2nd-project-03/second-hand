package team03.secondhand.domain.chat.dto;

import lombok.Getter;
import lombok.Setter;
import team03.secondhand.domain.chat.Chat;

public class ChatDataResponseDto {

    @Getter
    @Setter
    public static class Message {
        public enum MessageType {
            ENTER, TALK
        }
        private MessageType type;
        private String sender;
        private String message;

        public Message() {}

        public Message(Chat chat) {
            this.type = MessageType.TALK;
            this.sender = chat.getSender();
            this.message = chat.getContent();
        }
    }
}
