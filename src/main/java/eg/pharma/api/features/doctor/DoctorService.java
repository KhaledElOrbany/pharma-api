package eg.pharma.api.features.doctor;

import eg.pharma.api.exception.BusinessException;
import eg.pharma.api.features.doctor.dto.DoctorMapper;
import eg.pharma.api.features.doctorClass.DoctorClass;
import eg.pharma.api.features.doctorClass.DoctorClassService;
import eg.pharma.api.features.doctor.dto.DoctorRequest;
import eg.pharma.api.features.doctor.dto.DoctorResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorMapper doctorMapper;
    private final DoctorRepository doctorRepository;
    private final DoctorClassService doctorClassService;
    private final DoctorValidator doctorValidator;

    public DoctorService(
            DoctorMapper doctorMapper,
            DoctorRepository doctorRepository,
            DoctorClassService doctorClassService,
            DoctorValidator doctorValidator
    ) {
        this.doctorMapper = doctorMapper;
        this.doctorRepository = doctorRepository;
        this.doctorClassService = doctorClassService;
        this.doctorValidator = doctorValidator;
    }

    private Doctor findDoctorById(Long id) {
        return doctorRepository.findById(id).orElseThrow(() -> new BusinessException("Doctor not found", HttpStatus.NOT_FOUND));
    }

    public DoctorResource getDoctorById(Long id) {
        Doctor doctor = findDoctorById(id);
        return doctorMapper.toResource(doctor);
    }

    public List<DoctorResource> getAllDoctors(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Doctor> doctors = doctorRepository.findAll(pageable);
        return doctorMapper.toResourceList(doctors.getContent());
    }

    @Transactional
    public DoctorResource createDoctor(DoctorRequest doctorRequest) {
        Doctor doctor = doctorMapper.toEntity(doctorRequest);
        doctorValidator.validate(doctor);
        doctor = doctorRepository.save(doctor);
        return doctorMapper.toResource(doctor);
    }

    @Transactional
    public DoctorResource updateDoctor(Long id, DoctorRequest doctorRequest) {
        Doctor doctor = findDoctorById(id);
        doctorValidator.validate(doctor);
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
