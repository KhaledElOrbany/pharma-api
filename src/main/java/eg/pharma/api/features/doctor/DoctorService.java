package eg.pharma.api.features.doctor;

import eg.pharma.api.features.doctor.dto.DoctorMapper;
import eg.pharma.api.features.doctorClass.DoctorClass;
import eg.pharma.api.features.doctorClass.DoctorClassService;
import eg.pharma.api.features.doctor.dto.DoctorRequest;
import eg.pharma.api.features.doctor.dto.DoctorResource;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorMapper doctorMapper;
    private final DoctorRepository doctorRepository;
    private final DoctorClassService doctorClassService;

    public DoctorService(DoctorMapper doctorMapper, DoctorRepository doctorRepository, DoctorClassService doctorClassService) {
        this.doctorMapper = doctorMapper;
        this.doctorRepository = doctorRepository;
        this.doctorClassService = doctorClassService;
    }

    private Doctor findDoctorById(Long id) {
        return doctorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
    }

    public DoctorResource getDoctorById(Long id) {
        Doctor doctor = findDoctorById(id);
        return doctorMapper.toResource(doctor);
    }

    public List<DoctorResource> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctorMapper.toResourceList(doctors);
    }

    public DoctorResource createDoctor(DoctorRequest doctorRequest) {
        Doctor doctor = doctorMapper.toEntity(doctorRequest);
        doctor = doctorRepository.save(doctor);
        return doctorMapper.toResource(doctor);
    }

    public DoctorResource updateDoctor(Long id, DoctorRequest doctorRequest) {
        Doctor doctor = findDoctorById(id);
        doctor = doctorMapper.updateEntity(doctor, doctorRequest);
        doctor = doctorRepository.save(doctor);
        return doctorMapper.toResource(doctor);
    }

    public void deleteDoctor(Long id) {
        Doctor doctor = findDoctorById(id);
        doctorRepository.delete(doctor);
    }

    public DoctorResource assignClass(Long id, Long classId) {
        Doctor doctor = findDoctorById(id);
        DoctorClass doctorClass = doctorClassService.findDoctorClassById(classId);
        doctor.setDoctorClass(doctorClass);
        doctor = doctorRepository.save(doctor);
        return doctorMapper.toResource(doctor);
    }

    public List<DoctorResource> getDoctorsByClass(Long classId) {
        DoctorClass doctorClass = doctorClassService.findDoctorClassById(classId);
        List<Doctor> doctors = doctorRepository.findAllByDoctorClass(doctorClass);
        return doctorMapper.toResourceList(doctors);
    }
}
