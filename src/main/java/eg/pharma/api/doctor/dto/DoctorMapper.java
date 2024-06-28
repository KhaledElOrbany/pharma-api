package eg.pharma.api.doctor.dto;

import eg.pharma.api.config.interfaces.IMapper;
import eg.pharma.api.doctor.Doctor;
import eg.pharma.api.doctorClass.dto.DoctorClassMapper;
import eg.pharma.api.doctorClass.dto.DoctorClassResource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorMapper implements IMapper<Doctor, DoctorResource, DoctorRequest> {

    private final DoctorClassMapper doctorClassMapper;

    public DoctorMapper(@Lazy DoctorClassMapper doctorClassMapper) {
        this.doctorClassMapper = doctorClassMapper;
    }

    @Override
    public List<DoctorResource> toResourceList(List<Doctor> doctors) {
        return doctors.stream().map(this::toResource).toList();
    }

    @Override
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

    @Override
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

    @Override
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
