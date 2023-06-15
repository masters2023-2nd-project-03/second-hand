package team03.secondhand.domain.product.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseProductHomeDTO {
    private Long productId;
    private String title;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;
    private Integer price;
    private String location;
    private Long chatRoomCount;
    private List<Long> watchListMemberIdList;
    private String productImgUrl;

    // Getters and setters
    @Builder
    public ResponseProductHomeDTO(Long productId, String title, LocalDateTime createAt, LocalDateTime updatedAt, Integer price, String location, Long chatRoomCount, List<Long> watchListMemberIdList, String productImgUrl) {
        this.productId = productId;
        this.title = title;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
        this.price = price;
        this.location = location;
        this.chatRoomCount = chatRoomCount;
        this.watchListMemberIdList = watchListMemberIdList;
        this.productImgUrl = productImgUrl;
    }
}

