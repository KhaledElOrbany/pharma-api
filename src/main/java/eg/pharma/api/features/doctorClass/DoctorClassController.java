package eg.pharma.api.features.doctorClass;

import eg.pharma.api.base.ApiResponse;
import eg.pharma.api.base.BaseController;
import eg.pharma.api.features.doctorClass.dto.DoctorClassRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctorClass")
public class DoctorClassController extends BaseController {

    private final DoctorClassService doctorClassService;

    public DoctorClassController(DoctorClassService doctorClassService) {
        this.doctorClassService = doctorClassService;
    }

    @GetMapping(path = "/{id}")
    public ApiResponse getDoctorClassById(@PathVariable("id") Long id) {
        return respond(doctorClassService.getDoctorClassById(id));
    }

    @GetMapping(path = "/list")
    public ApiResponse getAllDoctorClasses() {
        return respond(doctorClassService.getAllDoctorClasses());
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
    public ResponseEntity<Void> deleteDoctorClass(@PathVariable("id") Long id) {
        doctorClassService.deleteDoctor(id);
        return ResponseEntity.ok().build();
    }
}
