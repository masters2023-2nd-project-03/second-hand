package team03.secondhand.domain;

import lombok.Getter;

@Getter
public class DataResponse<T> extends BaseResponse {
    T data;

    public DataResponse(StatusCode statusCode, T data) {
        super(statusCode);
        this.data = data;
    }
}
