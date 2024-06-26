package eg.pharma.doctor.dto;

import eg.enums.Specialization;

public class DoctorRequest {
    private String firstName;
    private String lastName;
    private Specialization specialization;
    private String address;
    private String phone;
    private String clinicPhone;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getClinicPhone() {
        return clinicPhone;
    }
}
