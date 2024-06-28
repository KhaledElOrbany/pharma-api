package eg.pharma.api.doctorClass.dto;

import eg.pharma.api.doctor.dto.DoctorMapper;
import eg.pharma.api.doctor.dto.DoctorResource;
import eg.pharma.api.doctorClass.DoctorClass;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorClassMapper {

    private final DoctorMapper doctorMapper;

    public DoctorClassMapper(@Lazy DoctorMapper doctorMapper) {
        this.doctorMapper = doctorMapper;
    }

    public List<DoctorClassResource> toResourceList(List<DoctorClass> doctorClasses) {
        return doctorClasses.stream().map(this::toResource).toList();
    }

    public DoctorClassResource toResource(DoctorClass doctorClass) {
        if (doctorClass == null) {
            return null;
        }

        List<DoctorResource> doctors = doctorMapper.toShallowResourceList(new ArrayList<>(doctorClass.getDoctors()));
        return new DoctorClassResource(
                doctorClass.getId(),
                doctorClass.getName(),
                doctorClass.getVisitCount(),
                doctorClass.getNotes(),
                doctorClass.getActive(),
                doctors
        );
    }

    public DoctorClassResource toShallowResource(DoctorClass doctorClass) {
        if (doctorClass == null) {
            return null;
        }

        return new DoctorClassResource(
                doctorClass.getId(),
                doctorClass.getName(),
                doctorClass.getVisitCount(),
                doctorClass.getNotes(),
                doctorClass.getActive(),
                null
        );
    }

    public DoctorClass toEntity(DoctorClassRequest doctorRequest) {
        return new DoctorClass(
                doctorRequest.getName(),
                doctorRequest.getVisitCount(),
                doctorRequest.getNotes(),
                doctorRequest.getIsActive()
        );
    }

    public DoctorClass updateEntity(DoctorClass doctorClass, DoctorClassRequest doctorClassRequest) {
        doctorClass.setName(doctorClassRequest.getName());
        doctorClass.setVisitCount(doctorClassRequest.getVisitCount());
        doctorClass.setNotes(doctorClassRequest.getNotes());
        doctorClass.setActive(doctorClassRequest.getIsActive());
        return doctorClass;
    }
}
