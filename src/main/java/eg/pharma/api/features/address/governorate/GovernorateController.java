package eg.pharma.api.features.address.governorate;

import eg.pharma.api.base.ApiResponse;
import eg.pharma.api.base.BaseController;
import eg.pharma.api.features.address.governorate.dto.GovernorateRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/governorate")
public class GovernorateController extends BaseController {

    private final GovernorateService governorateService;

    public GovernorateController(GovernorateService governorateService) {
        this.governorateService = governorateService;
    }

    @GetMapping("/{id}")
    public ApiResponse getGovernorateById(@PathVariable("id") Long id) {
        return respond(governorateService.getGovernorateById(id));
    }

    @GetMapping(path = "/list")
    public ApiResponse getAllGovernorates() {
        List<?> data = governorateService.getAllGovernorates();
        return respond(data, new HashMap<>() {{
            put("total", data.size());
        }});
    }

    @PostMapping
    public ApiResponse createGovernorate(@RequestBody GovernorateRequest governorateRequest) {
        return respond(governorateService.createGovernorate(governorateRequest));
    }

    @PutMapping("/{id}")
    public ApiResponse updateGovernorate(@PathVariable("id") Long id, @RequestBody GovernorateRequest governorateRequest) {
        return respond(governorateService.updateGovernorate(id, governorateRequest));
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteGovernorate(@PathVariable("id") Long id) {
        governorateService.deleteGovernorate(id);
        return respond();
    }
}
