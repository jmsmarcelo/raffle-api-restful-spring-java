package jmsmarcelo.raffleapi.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class ValidationException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    private final ExceptionDetails exceptionDetails;
    private final HttpStatus httpStatus;

    public ValidationException(HttpStatus httpStatus, String ...keyName) {
        this.httpStatus = httpStatus;
        exceptionDetails = new ExceptionDetails(httpStatus, keyName);
    }
    public ExceptionDetails getExceptionDetails() {
        return exceptionDetails;
    }
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
