package eg.pharma.api.features.address.city;

import eg.pharma.api.features.address.governorate.Governorate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findAllByGovernorate(Governorate governorate);
}
