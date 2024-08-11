package eg.pharma.api.features.user;

import eg.pharma.api.base.ApiResponse;
import eg.pharma.api.base.BaseController;
import eg.pharma.api.features.user.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {

    private final UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping(path = "/current")
    public ApiResponse getCurrentUser() {
        return respond(userService.getCurrentUser());
    }

    @GetMapping(path = "/{id}")
    public ApiResponse getUserById(@PathVariable("id") Long id) {
        return respond(userService.getUserById(id));
    }

    @GetMapping(path = "/list")
    public ApiResponse getAllUsers() {
        List<?> data = userService.getAllUsers(page, size);
        return respond(data, new HashMap<>() {{
            put("tableMetaData", getTablesMetaData("usersList"));
            put("total", data.size());
            put("page", page);
            put("size", size);
        }});
    }

    @PostMapping("/create")
    public ApiResponse create(@RequestBody UserRequest request) {
        return respond(userService.create(request));
    }

    @PutMapping(path = "/update/{id}")
    public ApiResponse updateUser(@PathVariable("id") Long id, @RequestBody UserRequest userRequest) {
        return respond(userService.updateUser(id, userRequest));
    }

    @DeleteMapping(path = "/{id}")
    public ApiResponse deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return respond();
    }

    @PostMapping("/resetPassword")
    public ApiResponse resetPassword(@RequestBody HashMap<String, String> requestBody) {
        return respond(userService.resetPassword(requestBody));
    }
}
