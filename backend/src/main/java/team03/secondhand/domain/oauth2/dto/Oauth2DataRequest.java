package team03.secondhand.domain.oauth2.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Oauth2DataRequest {
    @Data
    public static class Login {
        private String platform;
        private String code;

    }
}
