package team03.secondhand.domain.oauth2.error;

import lombok.Data;
import team03.secondhand.domain.oauth2.dto.Oauth2Data;

@Data
public class Oauth2Error{

    @Data
    public static class RequireRegistration extends RuntimeException{
        private Oauth2Data.LoginInfo loginInfo;

        public RequireRegistration(Oauth2Data.LoginInfo loginInfo) {
            this.loginInfo = loginInfo;
        }

        public Oauth2Data.LoginInfo getLoginInfo() {
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
