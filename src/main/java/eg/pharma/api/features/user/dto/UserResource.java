package eg.pharma.api.features.user.dto;

import eg.pharma.api.enums.Gender;
import eg.pharma.api.features.role.dto.RoleResource;

public record UserResource(
        Long id,
        String username,
        String firstName,
        String lastName,
        String phone,
        String email,
        Gender gender,
        RoleResource role
) {
}
