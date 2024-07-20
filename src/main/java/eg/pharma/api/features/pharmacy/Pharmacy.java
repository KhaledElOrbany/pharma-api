package eg.pharma.api.features.pharmacy;

import eg.pharma.api.features.audit.Audit;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;

@Entity
@Table(name = "pharmacy")
@SQLDelete(sql = "UPDATE pharmacy SET is_deleted = true WHERE id = ?")
public class Pharmacy extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String owner;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;

    Boolean isDeleted = Boolean.FALSE;

    public Pharmacy() {
    }

    public Pharmacy(String name, String owner, String address, String phone) {
        this.name = name;
        this.owner = owner;
        this.address = address;
        this.phone = phone;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
