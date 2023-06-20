package team03.secondhand.domain.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team03.secondhand.domain.category.dto.response.ResponseCategoryDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public List<ResponseCategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ResponseCategoryDTO convertToDTO(Category category) {
        return ResponseCategoryDTO.builder()
                .categoryId(category.getCategoryId())
                .title(category.getTitle())
                .categoryImgUrl(category.getCategoryImgUrl())
                .build();
    }
}
