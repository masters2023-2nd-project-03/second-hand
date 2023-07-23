package team03.secondhand.domain.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import team03.secondhand.domain.product.dto.query.ProductDetailDTO;
import team03.secondhand.domain.product.dto.query.ProductInfoDTO;
import team03.secondhand.domain.product.vo.ProductSearchCondition;

import org.springframework.transaction.annotation.Transactional;
import team03.secondhand.error.ProductError;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductQueryService {
    private final ProductQueryRepository productQueryRepository;


    public List<ProductInfoDTO> getProductsBy(Long memberId, ProductSearchCondition condition, Pageable pageable) {
        List<Product> products = productQueryRepository.getProductsBy(condition, pageable);

        return products.stream()
                .map(product -> new ProductInfoDTO(memberId, product))
                .collect(Collectors.toList());
    }


}
