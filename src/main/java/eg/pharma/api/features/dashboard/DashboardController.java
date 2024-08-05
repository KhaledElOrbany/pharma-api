package eg.pharma.api.features.dashboard;

import eg.pharma.api.base.BaseController;
import eg.pharma.api.features.tablesmetadata.TablesMetaDataService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController extends BaseController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService, TablesMetaDataService tablesMetaDataService) {
        super(tablesMetaDataService);
        this.dashboardService = dashboardService;
    }
}
