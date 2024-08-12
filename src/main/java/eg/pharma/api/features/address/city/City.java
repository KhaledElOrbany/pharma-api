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
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "governorate_id", referencedColumnName = "id")
    private Governorate governorate;

    public City() {
    }

    public City(String name) {
        this.name = name;
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

    public Governorate getGovernorate() {
        return governorate;
    }

    public void setGovernorate(Governorate governorate) {
        this.governorate = governorate;
    }
}

