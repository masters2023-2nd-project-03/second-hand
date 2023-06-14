package team03.secondhand.domain.member;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class MemberControllerAdvise {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> loginFailHandler(ResponseStatusException responseStatusException) {
        return ResponseEntity
                .status(responseStatusException.getStatus())
                .body(responseStatusException.getMessage());
    }
}
