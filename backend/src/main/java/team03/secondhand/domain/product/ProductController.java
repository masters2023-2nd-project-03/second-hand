package team03.secondhand.domain.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team03.secondhand.domain.product.dto.request.RequestProductCreateDTO;
import team03.secondhand.domain.product.dto.response.ResponseProductCreateDTO;
import team03.secondhand.domain.product.dto.response.ResponseProductHomeDTO;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ResponseProductCreateDTO> createProduct(@RequestBody RequestProductCreateDTO requestDTO) {
        log.debug("새로운 물품 등록");
        ResponseProductCreateDTO createdProduct = productService.createProduct(requestDTO);
        return ResponseEntity.ok(createdProduct);
    }

    @GetMapping
    public ResponseEntity<List<ResponseProductHomeDTO>> getAllProductByFilter(@RequestParam("location_id") Long locationId, @RequestParam("category_id") Long categoryId) {
        log.debug("물품 목록 응답(필터 적용)");
        List<ResponseProductHomeDTO> products = productService.getAllProductByFilter(locationId, categoryId);
        return ResponseEntity.ok(products);
    }

    // Other controller methods
}

