package eg.pharma.api.base;

import eg.pharma.api.features.user.User;
import org.springframework.security.core.context.SecurityContextHolder;
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

    protected <T, M> HashMap<String, Object> respond(T data, M meta) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("data", data);
        response.put("meta", meta);

        return response;
    }
}
