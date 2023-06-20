package team03.secondhand.domain.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team03.secondhand.domain.category.dto.response.ResponseCategoryDTO;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/categories")
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<ResponseCategoryDTO>> getAllCategories() {
        log.debug("카테고리 목록 호출");
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
}
