package team03.secondhand.domain.product.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class ProductDataRequestDTO {

    @NotBlank(message = "제목을 설정해주세요")
    @Size(min = 1, max = 120, message = "제목의 길이는 1이상 120이하여야 합니다.")
    private String title;
    private Integer price;
    @NotBlank(message = "글을 작성해주세요")
    private String content;
    @PositiveOrZero(message = "카테고리 ID를 입력해주세요")
    private Long categoryId;
    @PositiveOrZero(message = "동네 ID를 입력해주세요")
    private Long locationId;
    @NotBlank
    @Size(min = 1, max = 10, message = "제품 사진은 1장부터 10장까지 저장할 수 있습니다.")
    private List<MultipartFile> productImageUrls;
}

