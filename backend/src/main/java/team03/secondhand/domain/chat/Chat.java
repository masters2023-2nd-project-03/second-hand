package team03.secondhand.domain.chat;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team03.secondhand.domain.ChatRoom.ChatRoom;
import team03.secondhand.domain.member.Member;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "chat")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long chatId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_id")
    private Member sender;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

//    @Column(name = "read_status")
//    private String readStatus = "notUsed";


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chatroom_id")
    private ChatRoom chatRoom;

    @Builder
    public Chat(Member sender, String content, ChatRoom chatRoom) {
        this.sender = sender;
        this.content = content;
        this.chatRoom = chatRoom;
    }

    public String getSender() {
        return this.sender.getNickname();
    }

}
