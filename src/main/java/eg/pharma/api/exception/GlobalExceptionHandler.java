package eg.pharma.api.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
}
