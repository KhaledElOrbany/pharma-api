package eg.pharma.api.base;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

public class BaseController {

    protected Map<String, String[]> params;

    @ModelAttribute
    protected void setWebRequest(WebRequest webRequest) {
        this.params = webRequest.getParameterMap();
    }

    protected <T> ApiResponse respond(T data) {
        return ApiResponse.respond(data, HttpStatus.OK);
    }

    protected <T, M> ApiResponse respond(T data, M meta) {
        return ApiResponse.respond(data, meta, HttpStatus.OK);
    }

//    protected ResponseEntity<ErrorResponse> respond(ErrorResponse errorResponse, HttpStatus status) {
//        return new ApiResponse<>(errorResponse, status);
//    }
}
