package eg.pharma.api.features.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    User findByEmail(String email);

    @NonNull
    @Query("SELECT u FROM User u WHERE u.role.name <> 'SUPER_ADMIN'")
    Page<User> findAll(@NonNull Pageable pageable);

    @NonNull
    @Query("SELECT u FROM User u WHERE u.isDeleted = :isDeleted AND u.role.name <> 'SUPER_ADMIN'")
    Page<User> findAllByIsDeleted(@Param("isDeleted") boolean isDeleted, Pageable pageable);
}
