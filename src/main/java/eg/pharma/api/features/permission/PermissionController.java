package eg.pharma.api.features.permission;

import eg.pharma.api.base.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/permission")
public class PermissionController extends BaseController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        super();
        this.permissionService = permissionService;
    }
}
