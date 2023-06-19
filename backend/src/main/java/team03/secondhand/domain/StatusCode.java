package team03.secondhand.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@Getter
@AllArgsConstructor
public enum StatusCode {
    RESPONSE_SUCCESS(true, OK, 20000, "요청이 완료되었습니다."),
    REQUEST_SUCCESS(true, OK, 20001, "물품 등록이 완료되었습니다."),
    RESPONSE_FAILURE(false,  UNAUTHORIZED, 40000, "요청이 실패하였습니다."),
    TOKEN_INVALID(false, UNAUTHORIZED, 40100, "유효하지 않은 토큰입니다."),
    TOKEN_EXPIRED(false, UNAUTHORIZED, 40101, "만료된 토큰입니다."),
    TOKEN_IS_NULL(false, UNAUTHORIZED, 40102, "토큰이 없습니다."),
    TOKEN_CAN_NOT_DECODE(false, UNAUTHORIZED, 40103, "올바르지 않은 토큰 형식입니다."),
    AUTHENTICATION_INVALID_MEMBER(false, UNAUTHORIZED, 40104, "이용할 수 없는 멤버입니다."),
    DUPLICATED_MEMBER(false, UNAUTHORIZED, 40105, "이미 회원가입된 멤버입니다."),
    NOT_FOUND_LOCATION(false, NOT_FOUND, 40106, "유효하지 않는 동네입니다."),
    NOT_FOUND_PLATFORM(false, NOT_FOUND, 40107, "유효하지 않는 플랫폼 입니다."),
    REQUIRED_SIGNUP(false, FORBIDDEN, 40301, "추가 회원가입이 필요한 멤버입니다.");

    private final boolean success;
    private final HttpStatus status;
    private final int code;
    private final String message;
}
