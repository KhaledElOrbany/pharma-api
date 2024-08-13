package eg.pharma.api.features.user;

import eg.pharma.api.exception.BusinessException;
import eg.pharma.api.interfaces.IValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserValidator implements IValidator<User> {

    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void validate(User user) {
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            throw new BusinessException(
                    "User with username " + user.getUsername() + " already exists!",
                    HttpStatus.CONFLICT
            );
        }
    }
}
