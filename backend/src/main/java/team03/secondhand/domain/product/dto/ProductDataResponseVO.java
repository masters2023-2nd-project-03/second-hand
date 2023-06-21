package team03.secondhand.domain.product.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductDataResponseVO {
    private final String location;
    private final Long chatRoomCount;
    private final Integer watchlistCount;
    private final Boolean isWatchlistChecked;
    private final String productMainImgUrl;

    @Builder
    public ProductDataResponseVO(String location, Long chatRoomCount, Integer watchlistCount, Boolean isWatchlistChecked, String productMainImgUrl) {
        this.location = location;
        this.chatRoomCount = chatRoomCount;
        this.watchlistCount = watchlistCount;
        this.isWatchlistChecked = isWatchlistChecked;
        this.productMainImgUrl = productMainImgUrl;
    }
}
