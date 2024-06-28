package eg.pharma.api.user.dto;

import eg.pharma.api.config.IMapper;
import eg.pharma.api.user.User;

import java.util.List;

public class UserMapper implements IMapper<User, UserResource, UserRequest> {

    @Override
    public List<UserResource> toResourceList(List<User> entities) {
        return List.of();
    }

    @Override
    public UserResource toResource(User entity) {
        return null;
    }

    @Override
    public User toEntity(UserRequest request) {
        return null;
    }

    @Override
    public User updateEntity(User entity, UserRequest request) {
        return null;
    }
}
