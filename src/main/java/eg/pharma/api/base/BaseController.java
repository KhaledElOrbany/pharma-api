package eg.pharma.api.base;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

public class BaseController {

    private WebRequest webRequest;
    protected Map<String, String[]> params;

    @ModelAttribute
    public void setWebRequest(WebRequest webRequest) {
        this.webRequest = webRequest;

        getParams();
    }

    protected void getParams() {
        this.params = webRequest.getParameterMap();
    }
}
