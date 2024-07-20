package eg.pharma.api.features.medicine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    Medicine findByIdAndIsDeleted(Long id, boolean isDeleted);
}
