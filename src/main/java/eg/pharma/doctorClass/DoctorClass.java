package eg.pharma.doctorClass;

import com.fasterxml.jackson.annotation.JsonIgnore;
import eg.pharma.audit.Audit;
import eg.pharma.doctor.Doctor;
import eg.pharma.doctor.dto.DoctorMapper;
import eg.pharma.doctor.dto.DoctorResource;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "doctor_class")
@SQLDelete(sql = "UPDATE doctor_class SET is_deleted = true WHERE id = ?")
public class DoctorClass extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer visitCount;

    private String notes;

    private Boolean isActive = Boolean.TRUE;
    private Boolean isDeleted = Boolean.FALSE;

    @JsonIgnore
    @OneToMany(mappedBy = "doctorClass")
    private Set<Doctor> doctors = new HashSet<>();

    public DoctorClass() {
    }

    public DoctorClass(String name, Integer visitCount, String notes, Boolean isActive) {
        this.name = name;
        this.visitCount = visitCount;
        this.notes = notes;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }
}
