package eg.pharma.api.doctor;

import eg.pharma.api.doctor.dto.DoctorRequest;
import eg.pharma.api.doctor.dto.DoctorResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping(path = "/{id}")
    public DoctorResource getDoctorById(@PathVariable("id") Long id) {
        return doctorService.getDoctorById(id);
    }

    @GetMapping(path = "/list")
    public List<DoctorResource> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @PostMapping
    public DoctorResource createDoctor(@RequestBody DoctorRequest doctorRequest) {
        return doctorService.createDoctor(doctorRequest);
    }

    @PutMapping(path = "/{id}")
    public DoctorResource updateDoctor(@PathVariable("id") Long id, @RequestBody DoctorRequest doctorRequest) {
        return doctorService.updateDoctor(id, doctorRequest);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable("id") Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/{id}/assignClass/{classId}")
    public DoctorResource assignClass(@PathVariable("id") Long id, @PathVariable("classId") Long classId) {
        return doctorService.assignClass(id, classId);
    }

    @GetMapping(path = "/listByClass/{classId}")
    public List<DoctorResource> getDoctorsByClass(@PathVariable("classId") Long classId) {
        return doctorService.getDoctorsByClass(classId);
    }
}
