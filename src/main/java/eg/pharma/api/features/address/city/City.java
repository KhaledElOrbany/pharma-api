package eg.pharma.api.features.address.city;

import eg.pharma.api.features.address.governorate.Governorate;
import eg.pharma.api.features.audit.Audit;
import jakarta.persistence.*;

@Entity
@Table(name = "city")
public class City extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nameAr;

    @Column(nullable = false)
    private String nameEn;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "governorate_id", referencedColumnName = "id")
    private Governorate governorate;

    public City() {
    }

    public City(Long id, String nameAr, String nameEn) {
        this.id = id;
        this.nameAr = nameAr;
        this.nameEn = nameEn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public Governorate getGovernorate() {
        return governorate;
    }

    public void setGovernorate(Governorate governorate) {
        this.governorate = governorate;
    }
}

