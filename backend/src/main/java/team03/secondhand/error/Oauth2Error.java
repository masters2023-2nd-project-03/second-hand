package team03.secondhand.error;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import team03.secondhand.domain.oauth2.dto.Oauth2DataDto;

@Getter
@Setter
public class Oauth2Error {

    @Getter
    @Setter
    public static class RequireRegistration extends RuntimeException {
        private Oauth2DataDto.LoginInfo loginInfo;

        public RequireRegistration(Oauth2DataDto.LoginInfo loginInfo) {
            this.loginInfo = loginInfo;
        }

        public Oauth2DataDto.LoginInfo getLoginInfo() {
            return loginInfo;
        }
    }

    @Getter
    @Setter
    public static class NotFoundPlatform extends RuntimeException {}

    @Getter
    @Setter
    public static class InvalidPlatform extends RuntimeException {}

    @Getter
    @Setter
    public static class TokenInvalid extends RuntimeException {}


}
