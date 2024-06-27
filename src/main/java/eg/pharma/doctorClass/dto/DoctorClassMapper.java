package eg.pharma.doctorClass.dto;

import eg.pharma.doctorClass.DoctorClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorClassMapper {
    public List<DoctorClassResource> toResourceList(List<DoctorClass> doctors) {
        return doctors.stream().map(this::toResource).toList();
    }

    public DoctorClassResource toResource(DoctorClass doctorClass) {
        return new DoctorClassResource(
                doctorClass.getId(),
                doctorClass.getName(),
                doctorClass.getVisitCount(),
                doctorClass.getNotes(),
                doctorClass.getActive()
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
