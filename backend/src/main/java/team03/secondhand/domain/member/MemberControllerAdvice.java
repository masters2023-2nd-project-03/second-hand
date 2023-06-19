package team03.secondhand.domain.member;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team03.secondhand.domain.BaseResponse;
import team03.secondhand.domain.StatusCode;
import team03.secondhand.domain.member.error.MemberError;

@RestControllerAdvice(basePackages = "team03.secondhand.domain.member")
public class MemberControllerAdvice {
    @ExceptionHandler(MemberError.DuplicatedMember.class)
    public BaseResponse duplicatedMemberHandler() {return new BaseResponse(StatusCode.DUPLICATED_MEMBER);}

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse methodArgumentErrorHandler(MethodArgumentNotValidException e) {
        return new BaseResponse(StatusCode.RESPONSE_FAILURE, e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler(Exception.class)
    public BaseResponse exceptionHandler(Exception e) {
        return new BaseResponse(StatusCode.RESPONSE_FAILURE);
    }

}
