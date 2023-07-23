package team03.secondhand.domain.product.dto.query;

import lombok.Data;
import team03.secondhand.domain.product.Product;
import team03.secondhand.domain.product.ProductState;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProductInfoDTO {
    private Long productId;
    private String title;
    private Integer price;
    private String categoryTitle;
    private String location;
    private Long chatRoomCount;
    private Integer watchListCount;
    private Boolean isWatchlistChecked;
    private List<String> imageList;
    private ProductState salesStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ProductInfoDTO(Long memberId, Product product) {
        this.productId = product.getProductId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.categoryTitle = product.getCategoryTitle();
        this.location = product.getLocationShortening();
        this.chatRoomCount = 0L;
        this.watchListCount = product.getWatchCount();
        this.isWatchlistChecked = product.isWatchedByMemberId(memberId);
        this.imageList = product.getImages();
        this.salesStatus = product.getSalesStatus();
        this.createdAt = product.getCreatedAt();
        this.updatedAt = product.getUpdatedAt();
    }
}
