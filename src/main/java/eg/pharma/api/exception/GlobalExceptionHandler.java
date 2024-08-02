package eg.pharma.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
        HashMap<?, ?> data = new HashMap<>() {{
            put("error", ex.getMessage());
        }};
        ErrorResponse errorResponse = new ErrorResponse(data, new HashMap<>() {});
        return new ResponseEntity<>(errorResponse, ex.getErrorCode());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        Object value = ex.getValue();
        HashMap<?, ?> data = new HashMap<>() {{
            put("error", String.format("An error occurred with request /%s", value));
        }};
        ErrorResponse errorResponse = new ErrorResponse(data, new HashMap<>() {});
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
