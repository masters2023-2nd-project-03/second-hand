package team03.secondhand.domain.productImg;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImgRepository extends JpaRepository<ProductImg, Long> {

    ProductImg findFirstByProduct_ProductId(Long productId);

}
