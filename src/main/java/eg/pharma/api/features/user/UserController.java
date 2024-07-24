package eg.pharma.api.features.user;

import eg.pharma.api.base.ApiResponse;
import eg.pharma.api.base.BaseController;
import eg.pharma.api.features.user.dto.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{id}")
    public ApiResponse getUserById(@PathVariable("id") Long id) {
        return respond(userService.getUserById(id));
    }

    @GetMapping("/list")
    public ApiResponse getAllUsers() {
        return respond(userService.getAllUsers());
    }

    @PostMapping("/create")
    public ApiResponse create(@RequestBody UserRequest request) {
        return respond(userService.create(request));
    }

    @PutMapping(path = "update/{id}")
    public ApiResponse updateUser(@PathVariable("id") Long id, @RequestBody UserRequest userRequest) {
        return respond(userService.updateUser(id, userRequest));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/resetPassword")
    public ApiResponse resetPassword(@RequestBody HashMap<String, String> requestBody) {
        return respond(userService.resetPassword(requestBody));
    }
}
