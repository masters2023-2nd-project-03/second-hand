package team03.secondhand.oauth2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team03.secondhand.oauth2.dto.MemberDto;
import team03.secondhand.oauth2.dto.response.ResponseLoginFail;
import team03.secondhand.oauth2.error.RequireRegistrationError;

import java.security.InvalidKeyException;

@RestControllerAdvice("team03.secondhand.oauth2")
public class Oauth2ControllerAdvise {

    @ExceptionHandler(RequireRegistrationError.class)
    public ResponseEntity<ResponseLoginFail> loginFailHandler(RequireRegistrationError requireRegistrationError) {
        MemberDto memberDto = requireRegistrationError.getMemberDto();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ResponseLoginFail(
                        memberDto.getNickname(),
                        memberDto.getProfileUrl(),
                        memberDto.getOauthId()));
    }

    @ExceptionHandler(InvalidKeyException.class)
    public ResponseEntity<String> invalidTokenHandler() {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("유효하지 않는 인증 코드");
    }

}
