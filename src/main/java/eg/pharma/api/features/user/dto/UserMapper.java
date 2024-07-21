package eg.pharma.api.features.user.dto;

import eg.pharma.api.features.role.dto.RoleMapper;
import eg.pharma.api.interfaces.IMapper;
import eg.pharma.api.features.user.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMapper implements IMapper<User, UserResource, UserRequest> {

    private final RoleMapper roleMapper;

    public UserMapper(@Lazy RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public List<UserResource> toResourceList(List<User> users) {
        return users.stream().map(this::toResource).toList();
    }

    @Override
    public UserResource toResource(User user) {
        return new UserResource(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhone(),
                roleMapper.toResource(user.getRole())
        );
    }

    @Override
    public User toEntity(UserRequest request) {
        return new User(
                request.getUsername(),
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                request.getPhone()
        );
    }

    @Override
    public User updateEntity(User user, UserRequest request) {
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhone(request.getPhone());
        return user;
    }
}
