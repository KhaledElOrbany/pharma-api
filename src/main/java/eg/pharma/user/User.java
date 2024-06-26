package eg.pharma.user;

import eg.pharma.audit.Audit;
import jakarta.persistence.*;

@Entity
@Table(name = "`user`")
public class User extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 11)
    private String phone;

    public User() {
    }

    public static User getCurrentUser() {
        return null;
    }
}
