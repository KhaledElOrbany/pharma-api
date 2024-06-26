package eg.pharma.doctor.dto;

import eg.enums.Specialization;

public record DoctorResource(
        Long id,
        String firstName,
        String lastName,
        Specialization specialization,
        String address,
        String phone,
        String clinicPhone
) {
}
