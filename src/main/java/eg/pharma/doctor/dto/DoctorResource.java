package eg.pharma.doctor.dto;

import eg.enums.Specialization;

public class DoctorResource {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final Specialization specialization;
    private final String address;
    private final String phone;
    private final String clinicPhone;

    public DoctorResource(Long id, String firstName, String lastName, Specialization specialization, String address, String phone, String clinicPhone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
        this.address = address;
        this.phone = phone;
        this.clinicPhone = clinicPhone;
    }

    public Long getId() {
        return id;
    }

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
