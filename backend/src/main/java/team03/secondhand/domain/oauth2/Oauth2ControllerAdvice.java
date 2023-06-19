package team03.secondhand.domain.oauth2;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team03.secondhand.domain.BaseResponse;
import team03.secondhand.domain.DataResponse;
import team03.secondhand.domain.StatusCode;
import team03.secondhand.domain.oauth2.dto.Oauth2DataDto;
import team03.secondhand.domain.oauth2.error.Oauth2Error;

@RestControllerAdvice("team03.secondhand.domain.oauth2")
public class Oauth2ControllerAdvice {

    @ExceptionHandler(Oauth2Error.RequireRegistration.class)
    public DataResponse<Oauth2DataDto.LoginInfo> loginFailHandler(Oauth2Error.RequireRegistration requireRegistration) {
        return new DataResponse<>(StatusCode.REQUIRED_SIGNUP, requireRegistration.getLoginInfo());
    }

    @ExceptionHandler(Oauth2Error.TokenInvalid.class)
    public BaseResponse invalidTokenHandler() {
        return new BaseResponse(StatusCode.TOKEN_INVALID);
    }

    @ExceptionHandler(Oauth2Error.NotFoundPlatform.class)
    public BaseResponse invalidPlatformHandler() {
        return new BaseResponse(StatusCode.NOT_FOUND_PLATFORM);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse globalErrorHandler(MethodArgumentNotValidException e) {
        return new BaseResponse(StatusCode.RESPONSE_FAILURE, e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

}
