package eg.pharma.api.features.doctorClass;

import eg.pharma.api.base.ApiResponse;
import eg.pharma.api.base.BaseController;
import eg.pharma.api.features.doctorClass.dto.DoctorClassRequest;
import eg.pharma.api.features.tablesmetadata.TablesMetaDataService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/doctorClass")
public class DoctorClassController extends BaseController {

    private final DoctorClassService doctorClassService;

    public DoctorClassController(DoctorClassService doctorClassService, TablesMetaDataService tablesMetaDataService) {
        super(tablesMetaDataService);
        this.doctorClassService = doctorClassService;
    }

    @GetMapping(path = "/{id}")
    public ApiResponse getDoctorClassById(@PathVariable("id") Long id) {
        return respond(doctorClassService.getDoctorClassById(id));
    }

    @GetMapping(path = "/list")
    public ApiResponse getAllDoctorClasses() {
        List<?> data = doctorClassService.getAllDoctorClasses(page, size);
        return respond(data, new HashMap<>() {{
            put("total", data.size());
            put("page", page);
            put("size", size);
        }});
    }


    @PostMapping
    public ApiResponse createDoctorClass(@RequestBody DoctorClassRequest doctorRequest) {
        return respond(doctorClassService.createDoctorClass(doctorRequest));
    }

    @PutMapping(path = "/{id}")
    public ApiResponse updateDoctorClass(@PathVariable("id") Long id, @RequestBody DoctorClassRequest doctorRequest) {
        return respond(doctorClassService.updateDoctorClass(id, doctorRequest));
    }

    @DeleteMapping(path = "/{id}")
    public ApiResponse deleteDoctorClass(@PathVariable("id") Long id) {
        doctorClassService.deleteDoctor(id);
        return respond();
    }
}
