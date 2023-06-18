package team03.secondhand.domain.member;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team03.secondhand.domain.BaseResponse;
import team03.secondhand.domain.StatusCode;
import team03.secondhand.domain.member.error.MemberError;

@RestControllerAdvice(basePackages = "team03.secondhand.domain.member")
public class MemberControllerAdvise {
    @ExceptionHandler(MemberError.DuplicatedUser.class)
    public BaseResponse duplicatedUserHandler() {
        return new BaseResponse(StatusCode.DUPLICATED_USER);
    }

    @ExceptionHandler(MemberError.TokenExpired.class)
    public BaseResponse tokenExpiredHandler() {
        return new BaseResponse(StatusCode.TOKEN_EXPIRED);
    }

    @ExceptionHandler(MemberError.TokenIsNull.class)
    public BaseResponse tokenIsNullHandler() {
        return new BaseResponse(StatusCode.TOKEN_IS_NULL);
    }

    @ExceptionHandler(MemberError.RequireRegistration.class)
    public BaseResponse requiredSignUpHandler() {
        return new BaseResponse(StatusCode.REQUIRED_SIGNUP);
    }

    @ExceptionHandler(MemberError.NotFoundLocation.class)
    public BaseResponse notFoundLocationHandler() {
        return new BaseResponse(StatusCode.NOT_FOUND_LOCATION);
    }

}
