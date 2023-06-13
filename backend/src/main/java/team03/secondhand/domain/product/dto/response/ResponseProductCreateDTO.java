package team03.secondhand.domain.product.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseProductCreateDTO {
    private Long productId;

    // Getters and setters
    @Builder
    public ResponseProductCreateDTO(Long productId) {
        this.productId = productId;
    }
}

