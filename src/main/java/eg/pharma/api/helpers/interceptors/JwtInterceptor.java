package eg.pharma.api.helpers.interceptors;

import eg.pharma.api.features.user.User;
import eg.pharma.api.helpers.services.JwtService;
import eg.pharma.api.helpers.utils.SecurityUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseCookie;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Optional;

@Service
public class JwtInterceptor implements HandlerInterceptor {

    private final JwtService jwtService;

    public JwtInterceptor(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    private Optional<Cookie> getRefreshTokenCookie(@NonNull HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return Optional.empty();
        }

        return Arrays.stream(cookies)
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst();
    }

    @Override
    public boolean preHandle(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object object
    ) {
        Optional<Cookie> refreshTokenCookie = getRefreshTokenCookie(request);
        refreshTokenCookie.ifPresent(cookie -> System.out.println(cookie.getValue()));

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
        if (currentUser == null) {
            return;
        }

        String refreshToken = jwtService.generateRefreshToken(currentUser);
        if (!refreshToken.isEmpty() || !refreshToken.isBlank()) {
            ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                    .httpOnly(true)
                    .secure(true)
                    .path("/")
                    .maxAge(jwtService.getExpirationTime())
                    .build();
            response.addHeader("Set-Cookie", cookie.toString());
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
