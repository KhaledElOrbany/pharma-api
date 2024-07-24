package eg.pharma.api.base;

import eg.pharma.api.exception.ErrorResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

public class BaseController {

    protected Map<String, String[]> params;

    @ModelAttribute
    protected void setWebRequest(WebRequest webRequest) {
        this.params = webRequest.getParameterMap();
    }

    protected <T> ApiResponse respond(T data) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("data", data);
        response.put("meta", new HashMap<>());
        return new ApiResponse(response, HttpStatus.OK);
    }

    protected <T, M> ApiResponse respond(T data, M meta) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("data", data);
        response.put("meta", meta);
        return new ApiResponse(response, HttpStatus.OK);
    }
}
