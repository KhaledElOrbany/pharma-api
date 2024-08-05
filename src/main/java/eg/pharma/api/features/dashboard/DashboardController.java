package eg.pharma.api.features.dashboard;

import eg.pharma.api.base.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController extends BaseController {

    public DashboardController(DashboardService dashboardService) {
        super();
    }
}
