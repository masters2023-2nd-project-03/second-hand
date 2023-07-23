package team03.secondhand.domain.product.vo;

import lombok.Getter;

@Getter
public class ProductSearchCondition {

    private Long locationId;
    private Long categoryId;

    public ProductSearchCondition(Long locationId, Long categoryId) {
        this.locationId = locationId;
        this.categoryId = categoryId;
    }

}
