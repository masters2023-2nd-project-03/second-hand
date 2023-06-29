package team03.secondhand.domain.oauth2.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class Oauth2DataRequestDto {
    @Getter
    @Setter
    public static class Login {

        @NotBlank(message = "로그인을 위한 플랫폼 정보를 입력해주세요")
        private String platform;
        @NotBlank(message = "로그인을 위한 인증 코드를 입력해주세요")
        private String code;

    }
}
