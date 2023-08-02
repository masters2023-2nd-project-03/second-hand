package team03.secondhand.domain.ChatRoom;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team03.secondhand.domain.member.Member;
import team03.secondhand.domain.product.Product;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Getter
@Table(name = "chatroom")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatroom_id")
    private Long chatroomId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seller_id")
    private Member seller;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "buyer_id")
    private Member buyer;

    @Column(name = "create_at")
    private final LocalDateTime createdAt = LocalDateTime.now(ZoneId.of("Asia/Seoul"));

    @Column(name = "update_at")
    private final LocalDateTime updatedAt = LocalDateTime.now(ZoneId.of("Asia/Seoul"));

    @Builder
    public ChatRoom(Long chatroomId, Product product, Member seller, Member buyer) {
        this.chatroomId = chatroomId;
        this.product = product;
        this.seller = seller;
        this.buyer = buyer;
    }

}
