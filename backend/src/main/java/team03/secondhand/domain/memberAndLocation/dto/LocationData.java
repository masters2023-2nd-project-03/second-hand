package team03.secondhand.domain.memberAndLocation.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import team03.secondhand.domain.memberAndLocation.MemberAndLocation;

@Data
@NoArgsConstructor
public class LocationData {
    @Data
    public static class Info {
        private String locationDetails;

        private String locationShortening;

        private Boolean isMainLocation;

        public Info(MemberAndLocation memberAndLocation) {
            this.locationDetails = memberAndLocation.getLocation().getLocationDetails();
            this.locationShortening = memberAndLocation.getLocation().getLocationShortening();
            this.isMainLocation = memberAndLocation.getIsMainLocation();
        }
    }
    
}
