package eg.pharma.api.config;

import eg.pharma.api.exception.CustomAccessDeniedHandler;
import eg.pharma.api.features.user.UserDetailsServiceImpl;
import eg.pharma.api.helpers.filters.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    public SecurityConfig(
            UserDetailsServiceImpl userDetailsService,
            AuthenticationProvider authenticationProvider,
            JwtAuthenticationFilter jwtAuthenticationFilter,
            CustomAccessDeniedHandler customAccessDeniedHandler
    ) {
        this.userDetailsService = userDetailsService;
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        String[] adminAuthorities = new String[]{"ADMIN", "SUPER_ADMIN"};
        String[] allAuthorities = new String[]{"ADMIN", "SUPER_ADMIN", "USER"};

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/user/resetPassword").permitAll()
                        .requestMatchers("/api/user/update/").hasAnyAuthority(allAuthorities)
                        .requestMatchers("/api/user/**").hasAnyAuthority(adminAuthorities)
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedHandler(customAccessDeniedHandler)
                )
                .userDetailsService(userDetailsService)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
