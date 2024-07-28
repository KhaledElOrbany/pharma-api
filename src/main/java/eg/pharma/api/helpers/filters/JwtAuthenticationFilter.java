package eg.pharma.api.helpers.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import eg.pharma.api.exception.BusinessException;
import eg.pharma.api.exception.ErrorResponse;
import eg.pharma.api.helpers.services.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import org.springframework.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

@Service
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(
            JwtService jwtService,
            UserDetailsService userDetailsService
    ) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = authHeader.substring(7);
            String refreshToken = jwtService.retrieveRefreshToken(request);
            String username = extractUsername(request, token);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (username != null && authentication == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                try {
                    if (jwtService.isTokenValid(token, userDetails)) {
                        setAuthentication(userDetails, request);
                    }
                } catch (Exception ex) {
                    if (jwtService.isTokenValid(refreshToken, userDetails)) {
                        setAuthentication(userDetails, request);
                    }
                }

                filterChain.doFilter(request, response);
            }
        } catch (Exception ex) {
            handleError(response, ex);
        }
    }

    private String extractUsername(HttpServletRequest request, String token) {
        try {
            return jwtService.extractUsername(token);
        } catch (ExpiredJwtException ex) {
            String refreshToken = jwtService.retrieveRefreshToken(request);
            return jwtService.extractUsername(refreshToken);
        } catch (Exception ex) {
            throw new BusinessException();
        }
    }

    private void setAuthentication(UserDetails userDetails, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    private void handleError(
            @NonNull HttpServletResponse response,
            @NonNull Exception ex
    ) throws IOException {
        int status;
        String message;
        ObjectMapper objectMapper = new ObjectMapper();

        if (ex.getMessage().startsWith("JWT expired at")) {
            message = "Token expired!";
            status = HttpServletResponse.SC_FORBIDDEN;
        } else {
            message = "An error has occurred!";
            status = HttpServletResponse.SC_BAD_REQUEST;
        }

        response.setStatus(status);
        response.setContentType("application/json");
        HashMap<String, Object> data = new HashMap<>() {{
            put("error", message);
        }};
        HashMap<String, Object> meta = new HashMap<>() {{
            put("code", status);
        }};
        response.getWriter().write(objectMapper.writeValueAsString(new ErrorResponse(data, meta)));
    }
}
