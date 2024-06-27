package eg.pharma.dashboard;

import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final DashboardService dashboardService;

    public DashboardService(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }
}
