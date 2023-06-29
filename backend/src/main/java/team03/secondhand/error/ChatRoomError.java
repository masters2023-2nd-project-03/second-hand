package team03.secondhand.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomError {

    @Getter
    @Setter
    public static class NotFoundChatRoom extends RuntimeException {}

    @Getter
    @Setter
    public static class ConflictChatRoom extends RuntimeException {}

}
