package team03.secondhand.domain.product.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseProductHomeDTO {
    private Long productId;
    private String title;
    private LocalDateTime updatedAt;
    private Integer price;
    private String location;
    private Long chatRoomCount;
    private Long watchListCount;
    private String productImgUrl;

    // Getters and setters
    @Builder
    public ResponseProductHomeDTO(Long productId, String title, LocalDateTime updatedAt, Integer price, String location, Long chatRoomCount, Long watchListCount, String productImgUrl) {
        this.productId = productId;
        this.title = title;
        this.updatedAt = updatedAt;
        this.price = price;
        this.location = location;
        this.chatRoomCount = chatRoomCount;
        this.watchListCount = watchListCount;
        this.productImgUrl = productImgUrl;
    }
}

