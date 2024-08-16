package eg.pharma.api.features.doctorClass;

import eg.pharma.api.exception.BusinessException;
import eg.pharma.api.features.doctorClass.dto.DoctorClassMapper;
import eg.pharma.api.features.doctorClass.dto.DoctorClassRequest;
import eg.pharma.api.features.doctorClass.dto.DoctorClassResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DoctorClassService {

    private final DoctorClassMapper doctorClassMapper;
    private final DoctorClassRepository doctorClassRepository;

    public DoctorClassService(DoctorClassMapper doctorClassMapper, DoctorClassRepository doctorClassRepository) {
        this.doctorClassMapper = doctorClassMapper;
        this.doctorClassRepository = doctorClassRepository;
    }

    public DoctorClass findDoctorClassById(Long id) {
        DoctorClass doctorClass = doctorClassRepository.findByIdAndIsDeleted(id, false);
        if (doctorClass == null) {
            throw new BusinessException("Doctor class not found", HttpStatus.NOT_FOUND);
        }
        return doctorClass;
    }

    public DoctorClassResource getDoctorClassById(Long id) {
        DoctorClass doctorClass = findDoctorClassById(id);
        return doctorClassMapper.toResource(doctorClass);
    }

    public List<DoctorClassResource> getAllDoctorClasses(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DoctorClass> doctorClasses = doctorClassRepository.findAll(pageable);
        return doctorClassMapper.toResourceList(doctorClasses.getContent());
    }

    @Transactional
    public DoctorClassResource createDoctorClass(DoctorClassRequest doctorRequest) {
        DoctorClass doctorClass = doctorClassMapper.toEntity(doctorRequest);
        doctorClassRepository.save(doctorClass);
        return doctorClassMapper.toResource(doctorClass);
    }

    @Transactional
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
