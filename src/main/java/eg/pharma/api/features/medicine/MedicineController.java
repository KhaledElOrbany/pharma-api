package eg.pharma.api.features.medicine;

import eg.pharma.api.base.BaseController;
import eg.pharma.api.features.medicine.dto.MedicineRequest;
import eg.pharma.api.features.medicine.dto.MedicineResource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicine")
public class MedicineController extends BaseController {

    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping("/{id}")
    public MedicineResource getMedicineById(@PathVariable("id") Long id) {
        return medicineService.getMedicineById(id);
    }

    @GetMapping("/list")
    public List<MedicineResource> getAllMedicines() {
        return medicineService.getAllMedicines();
    }

    @PostMapping
    public MedicineResource createMedicine(@RequestBody MedicineRequest medicineRequest) {
        return medicineService.createMedicine(medicineRequest);
    }

    @PutMapping("/{id}")
    public MedicineResource updateMedicine(@PathVariable("id") Long id, @RequestBody MedicineRequest medicineRequest) {
        return medicineService.updateMedicine(id, medicineRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteMedicine(@PathVariable("id") Long id) {
        medicineService.deleteMedicine(id);
    }
}
