package eg.pharma.api.exception;

public class BusinessException extends RuntimeException {
    private String errorCode;

    public BusinessException() {
        super("An error has occurred, please contact us!");
    }

    public BusinessException(String message) {
        super(message);
        this.errorCode = "400";
    }

    public BusinessException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}

