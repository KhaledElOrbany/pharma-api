package eg.pharma.api.features.role.dto;

import eg.pharma.api.features.role.Role;
import eg.pharma.api.interfaces.IMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleMapper implements IMapper<Role, RoleResource, RoleRequest> {
    @Override
    public List<RoleResource> toResourceList(List<Role> roles) {
        return roles.stream().map(this::toResource).toList();
    }

    @Override
    public RoleResource toResource(Role role) {
        return new RoleResource(
                role.getId(),
                role.getName(),
                role.getDescription()
        );
    }

    @Override
    public Role toEntity(RoleRequest request) {
        return new Role(
                request.getName(),
                request.getDescription()
        );
    }

    @Override
    public Role updateEntity(Role role, RoleRequest request) {
        role.setName(request.getName());
        role.setDescription(request.getDescription());
        return role;
    }
}
