package team03.secondhand.domain.product.dto.query;

import lombok.Data;
import team03.secondhand.domain.product.Product;
import team03.secondhand.domain.product.ProductState;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProductDetailDTO {

    private Long productId;
    private String title;
    private ProductState salesStatus;
    private Long memberId;
    private String memberNickName;
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
    private int lookupCount;

    public ProductDetailDTO(Long memberId, Product product) {
        this.productId = product.getProductId();
        this.title = product.getTitle();
        this.contents = product.getContent();
        this.salesStatus = product.getSalesStatus();
        this.memberId = memberId;
        this.memberNickName = product.getSellerNickname();
        this.createAt = product.getCreatedAt();
        this.updatedAt = product.getUpdatedAt();
        this.price = product.getPrice();
        this.lookupCount = product.getLookupCount();

        this.location = product.getLocationShortening();
        this.categoryTitle = product.getCategoryTitle();
        this.watchlistCount = product.getWatchCount();
        this.isWatchlistChecked = product.isWatchedByMemberId(memberId);
        this.imageList = product.getImages();
        // TODO : 채팅 기능 구현시 추가
        this.chatRoomCount = 0L;
    }
}
