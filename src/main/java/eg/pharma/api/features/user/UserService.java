package eg.pharma.api.features.user;

import eg.pharma.api.base.BaseService;
import eg.pharma.api.exception.BusinessException;
import eg.pharma.api.features.user.dto.UserMapper;
import eg.pharma.api.features.user.dto.UserRequest;
import eg.pharma.api.features.user.dto.UserResource;
import eg.pharma.api.helpers.models.Mail;
import eg.pharma.api.helpers.services.MailService;
import eg.pharma.api.helpers.utils.SecurityUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class UserService extends BaseService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;
    private final UserValidator userValidator;

    public UserService(
            UserMapper userMapper,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder, MailService mailService,
            UserValidator userValidator
    ) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
        this.userValidator = userValidator;
    }

    public UserResource create(UserRequest userRequest) {
        User user = userMapper.toEntity(userRequest);
        userValidator.validate(user);

        String password = SecurityUtil.alphaNumericString(6);
        user.setPassword(passwordEncoder.encode(password));
        user = userRepository.save(user);

        if (!user.getEmail().isEmpty()) {
            String creator = user.getCreatedBy().getFirstName() + " " + user.getCreatedBy().getLastName();
            String body = "Your account has been created by " + creator +
                    ". Your username is: " + user.getUsername() + ", and your password is: " + password;

            mailService.sendEmail(new Mail(new String[]{user.getEmail()}, "Welcome to Pharma!", body));
        }

        return userMapper.toResource(user);
    }

    public UserResource getCurrentUser() {
        User currentUser = Objects.requireNonNull(SecurityUtil.getCurrentUser());
        return userMapper.toResource(currentUser);
    }

    private User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BusinessException("User not found", HttpStatus.NOT_FOUND));
    }

    public UserResource getUserById(Long id) {
        User user = findUserById(id);
        return userMapper.toResource(user);
    }

    public List<UserResource> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> users = userRepository.findAll(pageable);
        return userMapper.toResourceList(users.getContent());
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
        String message = "Please check your email!";

        User user = userRepository.findByEmail(email);
        if (user == null) {
            return message;
        }

        try {
            mailService.sendEmail(new Mail(new String[]{email}, "", ""));
        } catch (Exception ex) {
            throw new BusinessException("");
        }

        return message;
    }
}
