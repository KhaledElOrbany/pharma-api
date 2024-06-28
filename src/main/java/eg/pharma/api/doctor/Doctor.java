package eg.pharma.api.doctor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import eg.pharma.enums.Specialization;
import eg.pharma.api.audit.Audit;
import eg.pharma.api.doctorClass.DoctorClass;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;

@Entity
@Table(name = "doctor")
@SQLDelete(sql = "UPDATE doctor SET is_deleted = true WHERE id = ?")
public class Doctor extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Specialization specialization;

    @Column(nullable = false)
    private String address;

    private String phone;

    @Column(nullable = false)
    private String clinicPhone;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_class_id", referencedColumnName = "id")
    private DoctorClass doctorClass;

    Boolean isDeleted = Boolean.FALSE;

    public Doctor() {
    }

    public Doctor(String firstName, String lastName, Specialization specialization, String address, String phone, String clinicPhone) {
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getClinicPhone() {
        return clinicPhone;
    }

    public void setClinicPhone(String clinicPhone) {
        this.clinicPhone = clinicPhone;
    }

    public DoctorClass getDoctorClass() {
        return doctorClass;
    }

    public void setDoctorClass(DoctorClass doctorClass) {
        this.doctorClass = doctorClass;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
