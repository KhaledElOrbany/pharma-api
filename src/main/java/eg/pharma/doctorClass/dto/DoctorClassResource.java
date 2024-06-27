package eg.pharma.doctorClass.dto;

public record DoctorClassResource(
        Long id,
        String name,
        Integer visitCount,
        String notes,
        Boolean isActive
) {
}
