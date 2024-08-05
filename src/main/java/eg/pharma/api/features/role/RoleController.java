package eg.pharma.api.features.role;

import eg.pharma.api.base.BaseController;
import eg.pharma.api.features.tablesmetadata.TablesMetaDataService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
public class RoleController extends BaseController {

    private final RoleService roleService;

    public RoleController(RoleService roleService, TablesMetaDataService tablesMetaDataService) {
        super(tablesMetaDataService);
        this.roleService = roleService;
    }
}
