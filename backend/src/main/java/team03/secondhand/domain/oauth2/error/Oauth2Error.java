package team03.secondhand.domain.oauth2.error;

import lombok.Data;
import team03.secondhand.domain.oauth2.dto.Oauth2DataDto;

@Data
public class Oauth2Error{

    @Data
    public static class RequireRegistration extends RuntimeException{
        private Oauth2DataDto.LoginInfo loginInfo;

        public RequireRegistration(Oauth2DataDto.LoginInfo loginInfo) {
            this.loginInfo = loginInfo;
        }

        public Oauth2DataDto.LoginInfo getLoginInfo() {
            return loginInfo;
        }
    }

    @Data
    public static class NotFoundPlatform extends RuntimeException {}
    @Data
    public static class InvalidPlatform extends RuntimeException {}
    @Data
    public static class TokenInvalid extends RuntimeException {}


}
