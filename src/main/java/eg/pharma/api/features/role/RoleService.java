package eg.pharma.api.features.role;

import eg.pharma.api.features.role.dto.RoleMapper;
import eg.pharma.api.features.role.dto.RoleResource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;

    public RoleService(RoleMapper roleMapper, RoleRepository roleRepository) {
        this.roleMapper = roleMapper;
        this.roleRepository = roleRepository;
    }

    public List<RoleResource> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roleMapper.toResourceList(roles);
    }
}
