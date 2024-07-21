package eg.pharma.api.features.user.dto;

import eg.pharma.api.features.role.dto.RoleResource;

public record UserResource(
        Long id,
        String username,
        String email,
        String firstName,
        String lastName,
        String phone,
        RoleResource role
) {
}
