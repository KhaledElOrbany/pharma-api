package eg.pharma.api.exception;

public record ErrorResponse(
        String message,
        String errorCode
) {
}
