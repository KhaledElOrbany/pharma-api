package eg.pharma.api.helpers.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import eg.pharma.api.exception.BusinessException;
import eg.pharma.api.exception.ErrorResponse;
import eg.pharma.api.helpers.services.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;

@Service
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
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

        String token = authHeader.substring(7);
        try {
            String username = jwtService.extractUsername(token);
            authenticateUser(request, username, token);
        } catch (ExpiredJwtException ex) {
            handleExpiredToken(request, response);
        } catch (Exception ex) {
            handleError(response, ex);
            return;
        }

        try {
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            throw new BusinessException(ex.getMessage());
        }
    }

    private void handleExpiredToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String refreshToken = jwtService.retrieveRefreshToken(request);
        try {
            String username = jwtService.extractUsername(refreshToken);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtService.isTokenValid(refreshToken, userDetails)) {
                setAuthentication(userDetails, request);
                String newToken = jwtService.generateToken(userDetails);
                response.setHeader("Authorization", newToken);
            }
        } catch (Exception e) {
            handleError(response, e);
        }
    }

    private void handleError(@NonNull HttpServletResponse response, @NonNull Exception ex) throws IOException {
        int status;
        String message;
        ObjectMapper objectMapper = new ObjectMapper();

        if (ex instanceof JwtException) {
            message = "Token is invalid!";
            status = HttpServletResponse.SC_UNAUTHORIZED;
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

    private void authenticateUser(HttpServletRequest request, String username, String token) {
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtService.isTokenValid(token, userDetails)) {
                setAuthentication(userDetails, request);
            }
        }
    }

    private void setAuthentication(UserDetails userDetails, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
