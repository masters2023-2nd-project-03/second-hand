package team03.secondhand.domain.product;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team03.secondhand.domain.BaseResponse;
import team03.secondhand.domain.StatusCode;

@RestControllerAdvice(basePackages = "team03.secondhand.domain.product")
public class ProductControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse methodArgumentErrorHandler(MethodArgumentNotValidException e) {
        return new BaseResponse(StatusCode.BAD_PARAMETER, e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public BaseResponse exceptionHandler(MissingServletRequestParameterException e) {
        return new BaseResponse(StatusCode.BAD_PARAMETER);
    }

    @ExceptionHandler(Exception.class)
    public BaseResponse exceptionHandler(Exception e) {
        return new BaseResponse(StatusCode.RESPONSE_FAILURE);
    }

}
