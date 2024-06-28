package eg.pharma.api.doctorClass;

import eg.pharma.api.doctorClass.dto.DoctorClassRequest;
import eg.pharma.api.doctorClass.dto.DoctorClassResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctorClass")
public class DoctorClassController {

    private final DoctorClassService doctorClassService;

    public DoctorClassController(DoctorClassService doctorClassService) {
        this.doctorClassService = doctorClassService;
    }

    @GetMapping(path = "/{id}")
    public DoctorClassResource getDoctorClassById(@PathVariable("id") Long id) {
        return doctorClassService.getDoctorClassById(id);
    }

    @GetMapping(path = "/list")
    public List<DoctorClassResource> getAllDoctorClasses() {
        return doctorClassService.getAllDoctorClasses();
    }


    @PostMapping
    public DoctorClassResource createDoctorClass(@RequestBody DoctorClassRequest doctorRequest) {
        return doctorClassService.createDoctorClass(doctorRequest);
    }

    @PutMapping(path = "/{id}")
    public DoctorClassResource updateDoctorClass(@PathVariable("id") Long id, @RequestBody DoctorClassRequest doctorRequest) {
        return doctorClassService.updateDoctorClass(id, doctorRequest);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteDoctorClass(@PathVariable("id") Long id) {
        doctorClassService.deleteDoctor(id);
        return ResponseEntity.ok().build();
    }
}
