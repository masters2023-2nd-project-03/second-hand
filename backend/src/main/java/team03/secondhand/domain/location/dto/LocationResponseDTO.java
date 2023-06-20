package team03.secondhand.domain.location.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team03.secondhand.domain.location.Location;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LocationResponseDTO {
    @Getter
    @NoArgsConstructor
    public static class Info {
        private Long locationId;
        private String locationDetails;
        private String locationShortening;

        public Info(Location location) {
            this.locationId = location.getLocationId();
            this.locationDetails = location.getLocationDetails();
            this.locationShortening = location.getLocationShortening();
        }
    }
}
