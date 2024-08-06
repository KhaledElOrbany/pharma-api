package eg.pharma.api.features.user.dto;

import eg.pharma.api.interfaces.IMapper;
import eg.pharma.api.features.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMapper implements IMapper<User, UserResource, UserRequest> {

    @Override
    public List<UserResource> toResourceList(List<User> users) {
        return users.stream().map(this::toResource).toList();
    }

    @Override
    public UserResource toResource(User user) {
        return new UserResource(
                user.getId(),
                user.getFirstName() + " " + user.getLastName(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhone(),
                user.getEmail(),
                user.getGender(),
                user.getAddress(),
                user.getCityName(),
                user.getRoleName(),
                user.getDeleted()
        );
    }

    @Override
    public User toEntity(UserRequest request) {
        return new User(
                request.getUsername(),
                request.getFirstName(),
                request.getLastName(),
                request.getPassword(),
                request.getPhone(),
                request.getEmail()
        );
    }

    @Override
    public User updateEntity(User user, UserRequest request) {
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhone(request.getPhone());
        user.setGender(request.getGender());
        user.setAddress(request.getDistrict());
        user.setCity(request.getCity());
        return user;
    }
}
