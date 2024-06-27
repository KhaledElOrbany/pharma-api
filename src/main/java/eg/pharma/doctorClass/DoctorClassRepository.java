package eg.pharma.doctorClass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorClassRepository extends JpaRepository<DoctorClass, Long> {
}
