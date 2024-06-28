package eg.pharma.doctor.dto;

import eg.pharma.doctor.Doctor;
import eg.pharma.doctorClass.dto.DoctorClassMapper;
import eg.pharma.doctorClass.dto.DoctorClassResource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorMapper {

    private final DoctorClassMapper doctorClassMapper;

    public DoctorMapper(@Lazy DoctorClassMapper doctorClassMapper) {
        this.doctorClassMapper = doctorClassMapper;
    }

    public List<DoctorResource> toResourceList(List<Doctor> doctors) {
        return doctors.stream().map(this::toResource).toList();
    }

    public DoctorResource toResource(Doctor doctor) {
        DoctorClassResource doctorClassResource = doctorClassMapper.toShallowResource(doctor.getDoctorClass());

        return new DoctorResource(
                doctor.getId(),
                doctor.getFirstName(),
                doctor.getLastName(),
                doctor.getSpecialization(),
                doctor.getAddress(),
                doctor.getPhone(),
                doctor.getClinicPhone(),
                doctorClassResource
        );
    }

    public List<DoctorResource> toShallowResourceList(List<Doctor> doctors) {
        return doctors.stream().map(this::toShallowResource).toList();
    }

    public DoctorResource toShallowResource(Doctor doctor) {
        return new DoctorResource(
                doctor.getId(),
                doctor.getFirstName(),
                doctor.getLastName(),
                doctor.getSpecialization(),
                doctor.getAddress(),
                doctor.getPhone(),
                doctor.getClinicPhone(),
                null
        );
    }

    public Doctor toEntity(DoctorRequest doctorRequest) {
        return new Doctor(
                doctorRequest.getFirstName(),
                doctorRequest.getLastName(),
                doctorRequest.getSpecialization(),
                doctorRequest.getAddress(),
                doctorRequest.getPhone(),
                doctorRequest.getClinicPhone()
        );
    }

    public Doctor updateEntity(Doctor doctor, DoctorRequest doctorRequest) {
        doctor.setFirstName(doctorRequest.getFirstName());
        doctor.setLastName(doctorRequest.getLastName());
        doctor.setSpecialization(doctorRequest.getSpecialization());
        doctor.setAddress(doctorRequest.getAddress());
        doctor.setPhone(doctorRequest.getPhone());
        doctor.setClinicPhone(doctorRequest.getClinicPhone());
        return doctor;
    }
}
