package eg.pharma.api.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        HashMap<String, String> data = new HashMap<>() {{
            put("error", "Access denied for the requested resource");
        }};
        HashMap<String, String> meta = new HashMap<>() {{
            put("code", "403");
        }};
        ErrorResponse errorResponse = new ErrorResponse(data, meta);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
