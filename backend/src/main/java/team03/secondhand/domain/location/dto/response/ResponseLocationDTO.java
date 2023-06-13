package team03.secondhand.domain.location.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseLocationDTO {
    private Long locationId;
    private String locationDetails;
    private String locationShortening;

    @Builder
    public ResponseLocationDTO(Long locationId, String locationDetails, String locationShortening) {
        this.locationId = locationId;
        this.locationDetails = locationDetails;
        this.locationShortening = locationShortening;
    }
}
