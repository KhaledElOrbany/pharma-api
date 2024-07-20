package eg.pharma.api.features.user;

import eg.pharma.api.base.BaseController;
import eg.pharma.api.features.user.dto.UserRequest;
import eg.pharma.api.features.user.dto.UserResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserResource> create(@RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.create(request));
    }
}
