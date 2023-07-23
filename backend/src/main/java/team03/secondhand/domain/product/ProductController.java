package team03.secondhand.domain.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import team03.secondhand.domain.DataResponse;
import team03.secondhand.domain.StatusCode;
import team03.secondhand.domain.product.dto.ProductDataRequestDTO;
import team03.secondhand.domain.product.dto.ProductDataResponseDTO;
import team03.secondhand.domain.product.dto.query.ProductDetailDTO;
import team03.secondhand.domain.product.dto.query.ProductInfoDTO;
import team03.secondhand.domain.product.vo.ProductSearchCondition;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductQueryService productQueryService;

    @PostMapping
    public DataResponse<ProductDataResponseDTO.SimpleInfo> createProduct(@RequestAttribute Long memberId, @ModelAttribute ProductDataRequestDTO requestDTO) {
        ProductDataResponseDTO.SimpleInfo createdProductInfo = productService.createProduct(memberId, requestDTO);
        return new DataResponse<>(StatusCode.REQUEST_SUCCESS, createdProductInfo);
    }

    // TODO: 프론트 테스트 끝나면 동네ID 부분 수정 바람(필수로)
    @GetMapping
    public DataResponse<List<ProductInfoDTO>> getProductsBy(@RequestAttribute Long memberId, @ModelAttribute ProductSearchCondition condition, Pageable pageable) {
        List<ProductInfoDTO> productsDTO = productQueryService.getProductsBy(memberId, condition, pageable);
        return new DataResponse<>(StatusCode.RESPONSE_SUCCESS, productsDTO);
    }

    @GetMapping("/{productId}")
    public DataResponse<ProductDetailDTO> getDetailProductBy(@RequestAttribute Long memberId, @PathVariable Long productId) {
        ProductDetailDTO productDetailDTO = productService.getDetailProductBy(memberId, productId);
        return new DataResponse<>(StatusCode.RESPONSE_SUCCESS, productDetailDTO);
    }

}
