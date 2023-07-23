package team03.secondhand.domain.memberAndLocation.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import team03.secondhand.domain.memberAndLocation.MemberAndLocation;

@Data
@NoArgsConstructor
public class LocationData {
    @Data
    public static class Info {
        private long locationId;
        private String locationDetails;
        private String locationShortening;
        private boolean mainLocationState;

        public Info(MemberAndLocation memberAndLocation) {
            this.locationId = memberAndLocation.getLocationId();
            this.locationDetails = memberAndLocation.getLocationDetails();
            this.locationShortening = memberAndLocation.getLocationShortening();
            this.mainLocationState = memberAndLocation.isMainLocation();
        }
    }

}
