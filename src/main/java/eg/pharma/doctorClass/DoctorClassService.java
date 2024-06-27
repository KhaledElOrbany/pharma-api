package eg.pharma.doctorClass;

import eg.pharma.doctorClass.dto.DoctorClassMapper;
import eg.pharma.doctorClass.dto.DoctorClassRequest;
import eg.pharma.doctorClass.dto.DoctorClassResource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorClassService {

    private final DoctorClassMapper doctorClassMapper;
    private final DoctorClassRepository doctorClassRepository;

    public DoctorClassService(DoctorClassMapper doctorClassMapper, DoctorClassRepository doctorClassRepository) {
        this.doctorClassMapper = doctorClassMapper;
        this.doctorClassRepository = doctorClassRepository;
    }

    private DoctorClass findDoctorClassById(Long id) {
        return doctorClassRepository.findByIdAndIsDeleted(id, false);
    }

    public DoctorClassResource getDoctorClassById(Long id) {
        DoctorClass doctorClass = findDoctorClassById(id);
        return doctorClassMapper.toResource(doctorClass);
    }

    public List<DoctorClassResource> getAllDoctorClasses() {
        List<DoctorClass> doctorClasses = doctorClassRepository.findAll();
        return doctorClassMapper.toResourceList(doctorClasses);
    }

    public DoctorClassResource createDoctorClass(DoctorClassRequest doctorRequest) {
        DoctorClass doctorClass = doctorClassMapper.toEntity(doctorRequest);
        doctorClassRepository.save(doctorClass);
        return doctorClassMapper.toResource(doctorClass);
    }

    public DoctorClassResource updateDoctorClass(Long id, DoctorClassRequest doctorRequest) {
        DoctorClass doctorClass = findDoctorClassById(id);
        doctorClass = doctorClassMapper.updateEntity(doctorClass, doctorRequest);
        doctorClassRepository.save(doctorClass);
        return doctorClassMapper.toResource(doctorClass);
    }

    public void deleteDoctor(Long id) {
        DoctorClass doctorClass = findDoctorClassById(id);
        doctorClassRepository.delete(doctorClass);
    }
}
