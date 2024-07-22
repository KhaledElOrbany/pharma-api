package eg.pharma.api.features.user;

import eg.pharma.api.exception.BusinessException;
import eg.pharma.api.features.user.dto.UserMapper;
import eg.pharma.api.features.user.dto.UserRequest;
import eg.pharma.api.features.user.dto.UserResource;
import eg.pharma.api.helpers.models.Mail;
import eg.pharma.api.helpers.services.MailService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    public UserService(
            UserMapper userMapper,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder, MailService mailService
    ) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }

    public UserResource create(UserRequest request) {
        Optional<User> existingUser = userRepository.findByUsername(request.getUsername());
        if (existingUser.isPresent()) {
            throw new BusinessException("User with username " + request.getUsername() + " already exists!", "409");
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

        if (user.getEmail() != null) {
            Mail mail = new Mail();
            mail.setSubject("Welcome to Pharma!");
            mail.setTo(new String[]{user.getEmail()});
            mail.setBody("Your account has been created by Dr.Wagdy. Your username is: " + user.getUsername());
            mailService.sendEmail(mail);
        }

        return userMapper.toResource(user);
    }

    private User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public UserResource getUserById(Long id) {
        User user = findUserById(id);
        return userMapper.toResource(user);
    }

    public List<UserResource> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toResourceList(users);
    }

    public UserResource updateUser(Long id, UserRequest userRequest) {
        User user = findUserById(id);
        user = userMapper.updateEntity(user, userRequest);
        user = userRepository.save(user);
        return userMapper.toResource(user);
    }

    public void deleteUser(Long id) {
        User user = findUserById(id);
        userRepository.delete(user);
    }

    public String resetPassword(HashMap<String, String> params) {
        String email = params.get("email");

        try {
            Mail mail = new Mail();
            mail.setBody("");
            mail.setTo(new String[]{email});
            mail.setSubject("");

            mailService.sendEmail(mail);
        } catch (Exception ex) {
           throw new BusinessException("");
        }

        return "Email sent successfully!";
    }
}
