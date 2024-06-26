package eg.pharma.doctor.dto;

public class DoctorResource {
    private Long id;
    private String firstName;
    private String lastName;
    private String specialization;
    private String address;
    private String phone;
    private String clinicPhone;

    public DoctorResource(Long id, String firstName, String lastName, String specialization, String address, String phone, String clinicPhone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
        this.address = address;
        this.phone = phone;
        this.clinicPhone = clinicPhone;
    }
}
