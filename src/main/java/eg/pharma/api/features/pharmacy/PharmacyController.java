package eg.pharma.api.features.pharmacy;

import eg.pharma.api.base.ApiResponse;
import eg.pharma.api.base.BaseController;
import eg.pharma.api.features.pharmacy.dto.PharmacyRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/pharmacy")
public class PharmacyController extends BaseController {

    private final PharmacyService pharmacyService;

    public PharmacyController(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    @GetMapping(path = "/{id}")
    public ApiResponse getPharmacyById(@PathVariable("id") Long id) {
        return respond(pharmacyService.getPharmacyById(id));
    }

    @GetMapping(path = "/list")
    public ApiResponse getAllPharmacies() {
        List<?> data = pharmacyService.getAllPharmacies();
        return respond(data, new HashMap<>() {{
            put("total", data.size());
        }});
    }

    @PostMapping
    public ApiResponse createPharmacy(PharmacyRequest pharmacyRequest) {
        return respond(pharmacyService.createPharmacy(pharmacyRequest));
    }

    @PutMapping("/{id}")
    public ApiResponse updatePharmacy(@PathVariable("id") Long id, PharmacyRequest pharmacyRequest) {
        return respond(pharmacyService.updatePharmacy(id, pharmacyRequest));
    }

    @DeleteMapping("/{id}")
    public ApiResponse deletePharmacy(@PathVariable("id") Long id) {
        pharmacyService.deletePharmacy(id);
        return respond();
    }
}
