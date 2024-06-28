package eg.pharma.api.pharmacy;

import eg.pharma.api.config.base.BaseController;
import eg.pharma.api.pharmacy.dto.PharmacyRequest;
import eg.pharma.api.pharmacy.dto.PharmacyResource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pharmacies")
public class PharmacyController extends BaseController {

    private final PharmacyService pharmacyService;

    public PharmacyController(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    @GetMapping(path = "/{id}")
    public PharmacyResource getPharmacyById(@PathVariable("id") Long id) {
        return pharmacyService.getPharmacyById(id);
    }

    @GetMapping("/list")
    public List<PharmacyResource> getAllPharmacies() {
        return pharmacyService.getAllPharmacies();
    }

    @PostMapping
    public PharmacyResource createPharmacy(PharmacyRequest pharmacyRequest) {
        return pharmacyService.createPharmacy(pharmacyRequest);
    }

    @PutMapping("/{id}")
    public PharmacyResource updatePharmacy(@PathVariable("id") Long id, PharmacyRequest pharmacyRequest) {
        return pharmacyService.updatePharmacy(id, pharmacyRequest);
    }

    @DeleteMapping("/{id}")
    public void deletePharmacy(@PathVariable("id") Long id) {
        pharmacyService.deletePharmacy(id);
    }
}
