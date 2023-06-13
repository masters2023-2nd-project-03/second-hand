package team03.secondhand.domain.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team03.secondhand.domain.product.dto.request.RequestProductCreateDTO;
import team03.secondhand.domain.product.dto.response.ResponseProductCreateDTO;

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

    // Other controller methods
}

