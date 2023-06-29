package team03.secondhand.domain.product.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team03.secondhand.domain.product.Product;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductDataResponseDTO {

    @Getter
    @NoArgsConstructor
    public static class SimpleInfo {
        private Long productId;

        public SimpleInfo(Product product) {
            this.productId = product.getProductId();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class HomeInfo extends SimpleInfo {
        private String title;
        private String salesStatus;
        private LocalDateTime createAt;
        private LocalDateTime updatedAt;
        private Integer price;
        private String location;
        private Long chatRoomCount;
        private Integer watchlistCount;
        private Boolean isWatchlistChecked;
        private String productMainImgUrl;

        public HomeInfo(Product product, ProductDataResponseVO responseVO) {
            super(product);
            this.title = product.getTitle();
            this.salesStatus = product.getSalesStatus();
            this.createAt = product.getCreatedAt();
            this.updatedAt = product.getUpdatedAt();
            this.price = product.getPrice();
            this.location = responseVO.getLocation();
            this.chatRoomCount = responseVO.getChatRoomCount();
            this.watchlistCount = responseVO.getWatchlistCount();
            this.isWatchlistChecked = responseVO.getIsWatchlistChecked();
            this.productMainImgUrl = responseVO.getProductMainImgUrl();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class DetailInfo extends SimpleInfo {
        private String title;
        private String salesStatus;
        private String contents;
        private LocalDateTime createAt;
        private LocalDateTime updatedAt;
        private Integer price;
        private String categoryTitle;
        private String location;
        private Long chatRoomCount;
        private Integer watchlistCount;
        private Boolean isWatchlistChecked;
        private List<String> imageList;

        public DetailInfo(Product product, Long memberId) {
            super(product);
            this.title = product.getTitle();
            this.contents = product.getContent();
            this.salesStatus = product.getSalesStatus();
            this.createAt = product.getCreatedAt();
            this.updatedAt = product.getUpdatedAt();
            this.price = product.getPrice();

            // TODO : VO 도입 생각
            this.location = product.getLocationShortening();
            this.categoryTitle = product.getCategoryTitle();
            this.watchlistCount = product.getWatchCount();
            this.isWatchlistChecked = product.isWatchedByMemberId(memberId);
            this.imageList = product.getImageList();
            // TODO : 채팅 기능 구현시 추가
            this.chatRoomCount = 0L;
        }
    }

}

