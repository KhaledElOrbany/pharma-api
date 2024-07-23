package eg.pharma.api.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

@Component
public class CustomExceptionHandlingFilter implements Filter {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {
        try {
            chain.doFilter(request, response);
        } catch (Exception ex) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            httpResponse.setContentType("application/json");
            HashMap<String, String> data = new HashMap<>() {{
                put("error", "An error has occurred, please contact us!");
            }};
            HashMap<String, String> meta = new HashMap<>() {{
                put("code", "400");
            }};
            ErrorResponse errorResponse = new ErrorResponse(data, meta);

            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        }
    }
}
