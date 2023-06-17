package team03.secondhand.domain.member;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team03.secondhand.domain.BaseResponse;
import team03.secondhand.domain.StatusCode;

import java.util.NoSuchElementException;

@RestControllerAdvice(basePackages = "team03.secondhand.domain.member")
public class MemberControllerAdvise {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public BaseResponse conflictHandler() {
        return new BaseResponse(StatusCode.DUPLICATED_USER);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public BaseResponse expiredTokenHandler() {
        return new BaseResponse(StatusCode.TOKEN_EXPIRED);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public BaseResponse notFoundMemberHandler() {
        return new BaseResponse(StatusCode.REQUIRED_SIGNUP);
    }

    @ExceptionHandler(NoSuchFieldError.class)
    public BaseResponse notFoundLocationHandler() {
        return new BaseResponse(StatusCode.NOT_FOUND_LOCATION);
    }

}
