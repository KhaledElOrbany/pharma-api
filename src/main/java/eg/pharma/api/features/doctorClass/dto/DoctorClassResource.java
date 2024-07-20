package eg.pharma.api.features.doctorClass.dto;

import eg.pharma.api.features.doctor.dto.DoctorResource;

import java.util.List;

public record DoctorClassResource(
        Long id,
        String name,
        Integer visitCount,
        String notes,
        Boolean isActive,
        List<DoctorResource> doctors
) {
}
