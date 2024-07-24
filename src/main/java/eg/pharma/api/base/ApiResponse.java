package eg.pharma.api.base;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public class ApiResponse extends ResponseEntity<HashMap<?, ?>> {

    public ApiResponse(HashMap<String, Object> body, HttpStatus status) {
        super(body, status);
    }

    public static ApiResponse respond(HttpStatus status) {
        return new ApiResponse(new HashMap<>(), status);
    }

    public static <T> ApiResponse respond(T data, HttpStatus status) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("data", data);
        return new ApiResponse(response, status);
    }

    public static <T, M> ApiResponse respond(T data, M meta, HttpStatus status) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("data", data);
        response.put("meta", meta);
        return new ApiResponse(response, status);
    }
}
