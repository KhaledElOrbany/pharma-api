package eg.pharma.api.doctor.dto;

import eg.pharma.enums.Specialization;
import eg.pharma.api.doctorClass.dto.DoctorClassResource;

public record DoctorResource(
        Long id,
        String firstName,
        String lastName,
        Specialization specialization,
        String address,
        String phone,
        String clinicPhone,
        DoctorClassResource doctorClass
) {
}
