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
    public DataResponse<ProductDataResponseDTO.SimpleInfo> createProduct(@RequestBody ProductDataRequestDTO requestDTO) {
        log.debug("새로운 물품 등록");
        ProductDataResponseDTO.SimpleInfo createdProductInfo = productService.createProduct(requestDTO);
        return new DataResponse<>(StatusCode.REQUEST_SUCCESS, createdProductInfo);
    }

    @GetMapping
    public DataResponse<List<ProductDataResponseDTO.HomeInfo>> getAllProductByFilter(@RequestParam("location-id") Long locationId, @RequestParam(value = "category-id", required = false) Long categoryId) {
        log.debug("물품 목록 응답(필터 적용)");
        List<ProductDataResponseDTO.HomeInfo> productInfoList = productService.getAllProductByFilter(locationId, categoryId);
        return new DataResponse<>(StatusCode.RESPONSE_SUCCESS, productInfoList);
    }

}

