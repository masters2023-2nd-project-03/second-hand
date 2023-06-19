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
        private List<Long> watchListMemberIds;
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
            this.watchListMemberIds = responseVO.getWatchListMemberIds();
            this.productMainImgUrl = responseVO.getProductMainImgUrl();
        }
    }

}

