package eg.pharma.doctor.dto;

import eg.pharma.doctor.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorMapper {

    public List<DoctorResource> toResourceList(List<Doctor> doctors) {
        return doctors.stream().map(this::toResource).toList();
    }

    public DoctorResource toResource(Doctor doctor) {
        return new DoctorResource(
                doctor.getId(),
                doctor.getFirstName(),
                doctor.getLastName(),
                doctor.getSpecialization().name(),
                doctor.getAddress(),
                doctor.getPhone(),
                doctor.getClinicPhone()
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
