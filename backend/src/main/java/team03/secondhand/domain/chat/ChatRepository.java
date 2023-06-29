package team03.secondhand.domain.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    @Query("SELECT c FROM Chat c " +
            "JOIN ChatRoom cr on c.chatRoom.chatroomId = cr.chatroomId " +
            "WHERE cr.chatroomId = :roomId " +
            "ORDER BY c.chatId")
    List<Chat> getChatByRoomId(@Param("roomId") Long roomId);

}
