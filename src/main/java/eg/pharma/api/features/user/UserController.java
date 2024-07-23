package eg.pharma.api.features.user;

import eg.pharma.api.base.BaseController;
import eg.pharma.api.features.user.dto.UserRequest;
import eg.pharma.api.features.user.dto.UserResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{id}")
    public UserResource getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/list")
    public List<UserResource> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/create")
    public UserResource create(@RequestBody UserRequest request) {
        return userService.create(request);
    }

    @PutMapping(path = "update/{id}")
    public UserResource updateUser(@PathVariable("id") Long id, @RequestBody UserRequest userRequest) {
        return userService.updateUser(id, userRequest);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(@RequestBody HashMap<String, String> requestBody) {
        return ResponseEntity.ok(userService.resetPassword(requestBody));
    }
}
