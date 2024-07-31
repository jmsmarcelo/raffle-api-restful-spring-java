package jmsmarcelo.raffleapi.exception;

public class ValidationException extends RuntimeException {
    private final ExceptionDetails exceptionDetails;

    public ValidationException(String ...keyName) {
        exceptionDetails = new ExceptionDetails(keyName);
    }

    public ExceptionDetails getExceptionDetails() {
        return exceptionDetails;
    }
}
