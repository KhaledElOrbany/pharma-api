package eg.pharma.api.helpers.interceptors;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Optional;

@Component
public class LocaleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String language = Optional.ofNullable(request.getHeader("language")).orElse("en");
        Locale locale = Locale.forLanguageTag(language);
        LocaleContextHolder.setLocale(locale);
        return true;
    }
}
