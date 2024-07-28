package eg.pharma.api.helpers.interceptors;

import eg.pharma.api.features.user.User;
import eg.pharma.api.helpers.services.JwtService;
import eg.pharma.api.helpers.utils.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Service
public class JwtInterceptor implements HandlerInterceptor {

    private final Logger log = LoggerFactory.getLogger(JwtInterceptor.class);

    private final JwtService jwtService;

    public JwtInterceptor(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public boolean preHandle(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object object
    ) {
        return true;
    }

    @Override
    public void postHandle(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object object,
            ModelAndView model
    ) {
        User currentUser = SecurityUtil.getCurrentUser();
        if (currentUser != null) {
            String refreshToken = jwtService.retrieveRefreshToken(request);

            try {
                jwtService.isTokenValid(refreshToken, currentUser);
            } catch (Exception ex) {
                log.warn("New refresh token is generated!");
                refreshToken = jwtService.generateRefreshToken(currentUser);
            }

            if (!refreshToken.isEmpty()) {
                ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                        .httpOnly(true)
                        .secure(false) //TODO
                        .path("/")
                        .sameSite("Strict")
                        .maxAge(jwtService.getRefreshTokenExpirationTime() / 1000)
                        .build();
                response.addHeader("Set-Cookie", cookie.toString());
            }
        }
    }

    @Override
    public void afterCompletion(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object object,
            Exception exception
    ) {
    }
}
