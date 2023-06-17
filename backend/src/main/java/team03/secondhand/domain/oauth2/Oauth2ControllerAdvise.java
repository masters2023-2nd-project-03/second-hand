package team03.secondhand.domain.oauth2;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team03.secondhand.domain.BaseResponse;
import team03.secondhand.domain.DataResponse;
import team03.secondhand.domain.StatusCode;
import team03.secondhand.domain.oauth2.dto.Oauth2Data;
import team03.secondhand.domain.oauth2.error.RequireRegistrationError;

import java.util.InvalidPropertiesFormatException;

@RestControllerAdvice("team03.secondhand.oauth2")
public class Oauth2ControllerAdvise {

    @ExceptionHandler(RequireRegistrationError.class)
    public DataResponse<Oauth2Data.LoginInfo> loginFailHandler(RequireRegistrationError requireRegistrationError) {

        return new DataResponse<>(StatusCode.REQUIRED_SIGNUP, requireRegistrationError.getLoginInfo());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public BaseResponse invalidTokenHandler() {
        return new BaseResponse(StatusCode.TOKEN_INVALID);
    }

    @ExceptionHandler(InvalidPropertiesFormatException.class)
    public BaseResponse invalidPlatformHandler() {
        return new BaseResponse(StatusCode.NOT_FOUND_PLATFORM);
    }

}
