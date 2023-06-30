package team03.secondhand.domain.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team03.secondhand.domain.DataResponse;
import team03.secondhand.domain.StatusCode;
import team03.secondhand.domain.category.dto.CategoryResponseDTO;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/categories")
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public DataResponse<List<CategoryResponseDTO.Info>> getAllCategories() {
        log.debug("카테고리 목록 호출");
        List<CategoryResponseDTO.Info> categoryInfoList = categoryService.getAllCategories();
        return new DataResponse<>(StatusCode.RESPONSE_SUCCESS, categoryInfoList);
    }
}
