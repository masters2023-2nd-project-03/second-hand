package team03.secondhand.domain.member;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice(basePackages = "team03.secondhand.domain.member")
public class MemberControllerAdvise {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> conflictHandler() {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("중복된 회원");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> expiredTokenHandler() {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("유효하지 않은 접속 코드");
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> notFoundMemberHandler() {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("존재하지 않는 회원");
    }

    @ExceptionHandler(NoSuchFieldError.class)
    public ResponseEntity<String> notFoundLocationHandler() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("존재하지 않는 동네");
    }

}
