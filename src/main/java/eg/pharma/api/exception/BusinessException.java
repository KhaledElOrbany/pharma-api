package eg.pharma.api.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {
    private HttpStatus errorCode;

    public BusinessException() {
        super("An error has occurred, please contact us!");
    }

    public BusinessException(String message) {
        super(message);
        this.errorCode = HttpStatus.BAD_REQUEST;
    }

    public BusinessException(String message, HttpStatus errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public HttpStatus getErrorCode() {
        return errorCode;
    }
}

