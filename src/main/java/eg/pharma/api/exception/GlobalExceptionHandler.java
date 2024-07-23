package eg.pharma.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
        HttpStatus status = HttpStatusMapper.getHttpStatus(ex.getErrorCode());
        HashMap<String, String> data = new HashMap<>() {{
            put("error", ex.getMessage());
        }};
        HashMap<String, String> meta = new HashMap<>() {{
            put("code", ex.getErrorCode());
        }};
        ErrorResponse errorResponse = new ErrorResponse(data, meta);
        return new ResponseEntity<>(errorResponse, status);
    }
}
