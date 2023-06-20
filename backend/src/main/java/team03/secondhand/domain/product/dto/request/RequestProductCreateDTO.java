package team03.secondhand.domain.product.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class RequestProductCreateDTO {
    private String title;
    private Integer price;
    private String content;
    private Long categoryId;
    private Long locationId;
    private Long memberId;
    private List<String> productImageUrls;
}

