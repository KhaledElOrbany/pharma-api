package eg.pharma.api.features.auth;

import eg.pharma.api.features.auth.dto.AuthenticationResponse;
import eg.pharma.api.features.auth.dto.LoginRequest;
import eg.pharma.api.features.auth.dto.RegistrationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/create")
    public ResponseEntity<AuthenticationResponse> create(@RequestBody RegistrationRequest request) {
        return ResponseEntity.ok(authenticationService.create(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
