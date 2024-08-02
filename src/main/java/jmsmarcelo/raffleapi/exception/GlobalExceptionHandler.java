package jmsmarcelo.raffleapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ExceptionDetails> handle(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(new ExceptionDetails(HttpStatus.BAD_REQUEST, e));
    }
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<ExceptionDetails> handle(HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest().body(new ExceptionDetails(e));
    }
    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<ExceptionDetails> handle(ValidationException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(e.getExceptionDetails());
    }
    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<ExceptionDetails> handle(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionDetails(HttpStatus.UNAUTHORIZED, "error::" + e.getMessage()));
    }
    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<ExceptionDetails> handle(BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionDetails(HttpStatus.UNAUTHORIZED, e));
    }
}
