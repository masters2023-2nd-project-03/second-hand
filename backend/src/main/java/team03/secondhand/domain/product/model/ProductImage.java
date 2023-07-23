package team03.secondhand.domain.product.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ProductImage {

    @Column(name = "product_img_id")
    private String productImgId;
    @Column(name = "img_url")
    private String imageUrl;

    public ProductImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
