package eg.pharma.api.exception;

import org.springframework.http.HttpStatus;

public class HttpStatusMapper {

    public static HttpStatus getHttpStatus(String errorCode) {
        return switch (errorCode) {
            case "403" -> HttpStatus.FORBIDDEN;
            case "404" -> HttpStatus.NOT_FOUND;
            case "409" -> HttpStatus.CONFLICT;
            case "500" -> HttpStatus.INTERNAL_SERVER_ERROR;
            default -> HttpStatus.BAD_REQUEST;
        };
    }
}
