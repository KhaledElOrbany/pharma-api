package eg.pharma.api.features.address.governorate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import eg.pharma.api.features.address.city.City;
import eg.pharma.api.features.audit.Audit;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "governorate")
public class Governorate extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "governorate")
    private Set<City> cities = new HashSet<>();

    public Governorate() {
    }

    public Governorate(String name) {
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

    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }
}
