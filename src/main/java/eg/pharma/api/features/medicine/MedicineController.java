package eg.pharma.api.features.medicine;

import eg.pharma.api.base.ApiResponse;
import eg.pharma.api.base.BaseController;
import eg.pharma.api.features.medicine.dto.MedicineRequest;
import eg.pharma.api.features.tablesmetadata.TablesMetaDataService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/medicine")
public class MedicineController extends BaseController {

    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService, TablesMetaDataService tablesMetaDataService) {
        super(tablesMetaDataService);
        this.medicineService = medicineService;
    }

    @GetMapping("/{id}")
    public ApiResponse getMedicineById(@PathVariable("id") Long id) {
        return respond(medicineService.getMedicineById(id));
    }

    @GetMapping(path = "/list")
    public ApiResponse getAllMedicines() {
        List<?> data = medicineService.getAllMedicines(page, size);
        return respond(data, new HashMap<>() {{
            put("total", data.size());
            put("page", page);
            put("size", size);
        }});
    }

    @PostMapping
    public ApiResponse createMedicine(@RequestBody MedicineRequest medicineRequest) {
        return respond(medicineService.createMedicine(medicineRequest));
    }

    @PutMapping("/{id}")
    public ApiResponse updateMedicine(@PathVariable("id") Long id, @RequestBody MedicineRequest medicineRequest) {
        return respond(medicineService.updateMedicine(id, medicineRequest));
    }

    @DeleteMapping("/{id}")
    public void deleteMedicine(@PathVariable("id") Long id) {
        medicineService.deleteMedicine(id);
    }
}
