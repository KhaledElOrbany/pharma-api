package eg.pharma.api.features.doctor;

import eg.pharma.api.base.ApiResponse;
import eg.pharma.api.base.BaseController;
import eg.pharma.api.features.doctor.dto.DoctorRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/api/doctor")
public class DoctorController extends BaseController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping(path = "/{id}")
    public ApiResponse getDoctorById(@PathVariable("id") Long id) {
        return respond(doctorService.getDoctorById(id));
    }

    @GetMapping(path = "/list")
    public ApiResponse getAllDoctors() {
        List<?> data = doctorService.getAllDoctors(page, size);
        return respond(data, new HashMap<>() {{
            put("total", data.size());
            put("page", page);
            put("size", size);
        }});
    }

    @PostMapping("/create")
    public ApiResponse createDoctor(@RequestBody DoctorRequest doctorRequest) {
        return respond(doctorService.createDoctor(doctorRequest));
    }

    @PutMapping(path = "/update/{id}")
    public ApiResponse updateDoctor(@PathVariable("id") Long id, @RequestBody DoctorRequest doctorRequest) {
        return respond(doctorService.updateDoctor(id, doctorRequest));
    }

    @DeleteMapping(path = "/{id}")
    public ApiResponse deleteDoctor(@PathVariable("id") Long id) {
        doctorService.deleteDoctor(id);
        return respond();
    }

    @PutMapping(path = "/{id}/assignClass/{classId}")
    public ApiResponse assignClass(@PathVariable("id") Long id, @PathVariable("classId") Long classId) {
        return respond(doctorService.assignClass(id, classId));
    }

    @GetMapping(path = "/listByClass/{classId}")
    public ApiResponse getDoctorsByClass(@PathVariable("classId") Long classId) {
        return respond(doctorService.getDoctorsByClass(classId));
    }
}
