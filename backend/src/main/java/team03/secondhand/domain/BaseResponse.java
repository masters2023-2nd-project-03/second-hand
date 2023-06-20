package team03.secondhand.domain;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseResponse {

    private final boolean success;
    private final HttpStatus status;
    private final int code;
    private final String message;

    public BaseResponse(StatusCode statusCode) {
        this.success = statusCode.isSuccess();
        this.status = statusCode.getStatus();
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
    }

    public BaseResponse(StatusCode statusCode, String message) {
        this.success = statusCode.isSuccess();
        this.status = statusCode.getStatus();
        this.code = statusCode.getCode();
        this.message = message;

    }
}
