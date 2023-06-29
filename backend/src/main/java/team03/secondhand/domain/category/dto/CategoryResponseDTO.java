package team03.secondhand.domain.category.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import team03.secondhand.domain.category.Category;

public class CategoryResponseDTO {

    @Getter
    @NoArgsConstructor
    public static class Info {
        private Long categoryId;
        private String title;
        private String categoryImgUrl;

        public Info(Category category) {
            this.categoryId = category.getCategoryId();
            this.title = category.getTitle();
            this.categoryImgUrl = category.getCategoryImgUrl();
        }
    }
}
