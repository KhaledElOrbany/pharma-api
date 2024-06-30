package eg.pharma.api.config.base;

import eg.pharma.api.Role.Role;
import eg.pharma.api.Role.RoleRepository;
import eg.pharma.api.permission.Permission;
import eg.pharma.api.permission.PermissionRepository;
import eg.pharma.api.user.User;
import eg.pharma.api.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Transactional
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SetupDataLoader(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PermissionRepository permissionRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
        Permission readPermission = createPermissionIfNotFound("READ_PRIVILEGE");
        Permission writePermission = createPermissionIfNotFound("WRITE_PRIVILEGE");

        Set<Permission> adminPermissions = Set.of(readPermission, writePermission);
        createRoleIfNotFound("ROLE_ADMIN", adminPermissions);
        createRoleIfNotFound("ROLE_USER", Set.of(readPermission));

        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        User user = new User(
                "Test",
                "Test",
                "test@test.com",
                passwordEncoder.encode("test"),
                "123456789"
        );
        user.setRoles(Set.of(adminRole));
        user.setEnabled(true);
        userRepository.save(user);

        alreadySetup = true;
    }

    Permission createPermissionIfNotFound(String name) {
        Permission permission = permissionRepository.findByName(name);
        if (permission == null) {
            permission = new Permission(name);
            permissionRepository.save(permission);
        }
        return permission;
    }

    void createRoleIfNotFound(String name, Set<Permission> permissions) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name, "");
            role.setPermissions(permissions);
            roleRepository.save(role);
        }
    }
}
