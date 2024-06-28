package eg.pharma.doctorClass.dto;

import eg.pharma.doctor.dto.DoctorResource;

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
