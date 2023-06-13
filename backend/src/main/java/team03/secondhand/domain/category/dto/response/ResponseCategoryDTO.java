package team03.secondhand.domain.category.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseCategoryDTO {
    private Long categoryId;
    private String title;
    private String categoryImgUrl;

    @Builder
    public ResponseCategoryDTO(Long categoryId, String title, String categoryImgUrl) {
        this.categoryId = categoryId;
        this.title = title;
        this.categoryImgUrl = categoryImgUrl;
    }
}
