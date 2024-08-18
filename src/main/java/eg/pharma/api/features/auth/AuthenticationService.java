package eg.pharma.api.features.auth;

import eg.pharma.api.exception.BusinessException;
import eg.pharma.api.features.user.User;
import eg.pharma.api.features.user.UserRepository;
import eg.pharma.api.features.auth.dto.LoginRequest;
import eg.pharma.api.features.auth.dto.AuthenticationResponse;
import eg.pharma.api.helpers.services.JwtService;
import eg.pharma.api.helpers.utils.EnvironmentUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            JwtService jwtService,
            UserRepository userRepository,
            AuthenticationManager authenticationManager
    ) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse authenticate(LoginRequest request, HttpServletResponse response) {
        try {
            var login = new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            );
            authenticationManager.authenticate(login);
        } catch (Exception ex) {
            throw new BusinessException(ex.getMessage());
        }

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new BusinessException("User not found", HttpStatus.NOT_FOUND));
        String token = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .secure(EnvironmentUtil.isProduction())
                .path("/")
                .sameSite("Strict")
                .maxAge(jwtService.getRefreshTokenExpirationTime() / 1000)
                .build();
        response.addHeader("Set-Cookie", cookie.toString());

        return new AuthenticationResponse(token, jwtService.getExpirationTime());
    }
}
