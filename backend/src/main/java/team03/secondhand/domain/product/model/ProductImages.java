package team03.secondhand.domain.product.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ProductImages {

    @ElementCollection
    @CollectionTable(
            name = "product_img",
            joinColumns = @JoinColumn(name = "product_id"))
    private List<ProductImage> productImages = new ArrayList<>();

    public ProductImages (List<String> imageUrls) {
        productImages = imageUrls.stream()
                .map(imageUrl -> new ProductImage(imageUrl))
                .collect(Collectors.toList());
    }

    public List<String> getImageUrlList() {
        return productImages.stream()
                .map(img -> img.getImageUrl())
                .collect(Collectors.toList());
    }

}
