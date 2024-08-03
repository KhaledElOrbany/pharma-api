package eg.pharma.api.features.doctor.dto;

import eg.pharma.api.enums.Specialization;
import eg.pharma.api.features.doctorClass.dto.DoctorClassResource;

public record DoctorResource(
        Long id,
        String firstName,
        String lastName,
        Specialization specialization,
        String address,
        String phone,
        String clinicPhone,
        DoctorClassResource doctorClass,
        boolean isDeleted
) {
}
