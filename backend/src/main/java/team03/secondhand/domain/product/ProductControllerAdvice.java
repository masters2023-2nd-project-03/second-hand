package team03.secondhand.domain.product;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team03.secondhand.domain.BaseResponse;
import team03.secondhand.domain.StatusCode;
import team03.secondhand.error.MemberError;
import team03.secondhand.error.ProductError;

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

    @ExceptionHandler(ProductError.NotFoundProduct.class)
    public BaseResponse notFoundLocationHandler() {
        return new BaseResponse(StatusCode.NOT_FOUND_PRODUCT);
    }

    @ExceptionHandler(Exception.class)
    public BaseResponse exceptionHandler(Exception e) {
        return new BaseResponse(StatusCode.RESPONSE_FAILURE);
    }

}
