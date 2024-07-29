package eg.pharma.api.base;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

public class BaseController {

    protected int page = 0;
    protected int size = 5;
    protected Map<String, String[]> params;

    public BaseController() {
    }

    @ModelAttribute
    protected void setWebRequest(WebRequest webRequest) {
        this.params = webRequest.getParameterMap();
        preparePagination();
    }

    protected ApiResponse respond() {
        return ApiResponse.respond(HttpStatus.OK);
    }

    protected <T> ApiResponse respond(T data) {
        return ApiResponse.respond(data, HttpStatus.OK);
    }

    protected <T, M> ApiResponse respond(T data, M meta) {
        return ApiResponse.respond(data, meta, HttpStatus.OK);
    }

    protected void preparePagination() {
        try {
            this.page = Integer.parseInt(params.get("page")[0]);
            this.size = Integer.parseInt(params.get("size")[0]);
        } catch (Exception e) {
            this.page = 0;
            this.size = 5;
        }
    }
}
