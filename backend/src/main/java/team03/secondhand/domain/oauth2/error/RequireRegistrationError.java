package team03.secondhand.domain.oauth2.error;

import team03.secondhand.domain.oauth2.dto.Oauth2Data;


public class RequireRegistrationError extends RuntimeException {

    private Oauth2Data.LoginInfo loginInfo;

    public RequireRegistrationError(Oauth2Data.LoginInfo loginInfo) {
        this.loginInfo = loginInfo;
    }

    public Oauth2Data.LoginInfo getLoginInfo() {
        return loginInfo;
    }

}
