package eg.pharma.api.base;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

public class BaseController {

    protected Map<String, String[]> params;

    @ModelAttribute
    public void setWebRequest(WebRequest webRequest) {
        this.params = webRequest.getParameterMap();
    }
}
