package eg.pharma.api.features.doctorClass.dto;

public class DoctorClassRequest {
    private String name;
    private Integer visitCount;
    private String notes;
    private Boolean isActive;

    public String getName() {
        return this.name;
    }

    public Integer getVisitCount() {
        return this.visitCount;
    }

    public String getNotes() {
        return this.notes;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }
}
