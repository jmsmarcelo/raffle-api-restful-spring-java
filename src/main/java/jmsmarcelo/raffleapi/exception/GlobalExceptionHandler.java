package jmsmarcelo.raffleapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ExceptionDetails> handle(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(new ExceptionDetails(e, HttpStatus.BAD_REQUEST));
    }
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<ExceptionDetails> handle(HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest().body(new ExceptionDetails(e));
    }
    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<ExceptionDetails> handle(ValidationException e) {
        return ResponseEntity.badRequest().body(e.getExceptionDetails());
    }
}
