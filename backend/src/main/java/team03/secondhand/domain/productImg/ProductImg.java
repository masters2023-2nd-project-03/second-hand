package team03.secondhand.domain.productImg;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team03.secondhand.domain.product.Product;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "product_img")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_img_id")
    private Long productImgId;

    @Column(name = "img_url")
    private String imgUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    // Getter and Setter methods
    @Builder
    public ProductImg(String imgUrl, Product product) {
        this.imgUrl = imgUrl;
        this.product = product;
    }
}
