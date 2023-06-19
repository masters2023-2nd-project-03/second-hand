package team03.secondhand.domain.oauth2.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class Oauth2DataRequestDto {
    @Getter @Setter
    public static class Login {
        private String platform;
        private String code;

    }
}
