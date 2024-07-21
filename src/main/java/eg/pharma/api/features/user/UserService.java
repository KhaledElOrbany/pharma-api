package eg.pharma.api.features.user;

import eg.pharma.api.features.user.dto.UserMapper;
import eg.pharma.api.features.user.dto.UserRequest;
import eg.pharma.api.features.user.dto.UserResource;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserMapper userMapper,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResource create(UserRequest request) {
        Optional<User> existingUser = userRepository.findByUsername(request.getUsername());
        if (existingUser.isPresent()) {
            throw new IllegalStateException("User with username " + request.getUsername() + " already exists!");
        }

        User user = new User(
                request.getUsername(),
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getPhone()
        );
        user.setRole(request.getRole());
        user = userRepository.save(user);

        return userMapper.toResource(user);
    }

    private User findDoctorById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public UserResource getUserById(Long id) {
        User user = findDoctorById(id);
        return userMapper.toResource(user);
    }

    public List<UserResource> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toResourceList(users);
    }
}
