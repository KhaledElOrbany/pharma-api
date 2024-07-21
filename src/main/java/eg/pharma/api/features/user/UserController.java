package eg.pharma.api.features.user;

import eg.pharma.api.base.BaseController;
import eg.pharma.api.features.user.dto.UserRequest;
import eg.pharma.api.features.user.dto.UserResource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public UserResource create(@RequestBody UserRequest request) {
        return userService.create(request);
    }


    @GetMapping(path = "/{id}")
    public UserResource getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/list")
    public List<UserResource> getAllUsers() {
        return userService.getAllUsers();
    }
}
