package eg.pharma.api.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

public class BusinessException extends RuntimeException implements HandlerExceptionResolver {
    private String errorCode;

    public BusinessException() {
        super("An error has occurred, please contact us!");
    }

    public BusinessException(String message) {
        super(message);
        this.errorCode = "400";
    }

    public BusinessException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public ModelAndView resolveException(
            HttpServletRequest request,
            HttpServletResponse response,
            Object object,
            Exception exception
    ) {
        ModelAndView model = new ModelAndView();
        model.setView(new MappingJackson2JsonView());
        model.addObject("error", "An error has occurred, please contact us!");
        model.setStatus(HttpStatusCode.valueOf(500));
        return model;
    }
}

