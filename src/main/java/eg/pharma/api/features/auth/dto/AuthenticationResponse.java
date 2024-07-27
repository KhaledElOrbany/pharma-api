package eg.pharma.api.features.auth.dto;

public record AuthenticationResponse(
        String token,
        Long tokenExpiresIn,
        String refreshToken,
        Long refreshTokenExpiresIn
) {
}
