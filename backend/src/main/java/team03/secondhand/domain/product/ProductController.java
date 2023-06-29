package team03.secondhand.domain.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import team03.secondhand.domain.DataResponse;
import team03.secondhand.domain.StatusCode;
import team03.secondhand.domain.product.dto.ProductDataRequestDTO;
import team03.secondhand.domain.product.dto.ProductDataResponseDTO;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public DataResponse<ProductDataResponseDTO.SimpleInfo> createProduct(@RequestAttribute Long memberId, @ModelAttribute ProductDataRequestDTO requestDTO) {
        log.debug("새로운 물품 등록");
        ProductDataResponseDTO.SimpleInfo createdProductInfo = productService.createProduct(memberId, requestDTO);
        return new DataResponse<>(StatusCode.REQUEST_SUCCESS, createdProductInfo);
    }

    // TODO: 프론트 테스트 끝나면 동네ID 부분 수정 바람(필수로)
    @GetMapping
    public DataResponse<List<ProductDataResponseDTO.HomeInfo>> getAllProductByFilter(@RequestAttribute Long memberId, @RequestParam(value = "location-id", required = false) Long locationId, @RequestParam(value = "category-id", required = false) Long categoryId) {
        log.debug("물품 목록 응답(필터 적용)");
        List<ProductDataResponseDTO.HomeInfo> productInfoList = productService.getAllProductByFilter(memberId, locationId, categoryId);
        return new DataResponse<>(StatusCode.RESPONSE_SUCCESS, productInfoList);
    }

    @GetMapping("/{productId}")
    public DataResponse<ProductDataResponseDTO.DetailInfo> getProduct(@RequestAttribute Long memberId, @PathVariable Long productId) {
        ProductDataResponseDTO.DetailInfo productDetailInfo = productService.getProductBy(memberId, productId);
        return new DataResponse<>(StatusCode.RESPONSE_SUCCESS, productDetailInfo);
    }

}
