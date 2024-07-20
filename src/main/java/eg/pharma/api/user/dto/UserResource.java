package eg.pharma.api.user.dto;

import eg.pharma.enums.Role;

import java.util.List;

public record UserResource(
        Long id,
        String username,
        String email,
        String firstName,
        String lastName,
        String phone,
        String address,
        List<Role> roles
) {
}
