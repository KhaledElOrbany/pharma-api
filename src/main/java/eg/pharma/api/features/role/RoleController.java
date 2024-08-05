package eg.pharma.api.features.role;

import eg.pharma.api.base.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
public class RoleController extends BaseController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        super();
        this.roleService = roleService;
    }
}
