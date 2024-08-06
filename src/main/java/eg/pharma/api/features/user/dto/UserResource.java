package eg.pharma.api.features.user.dto;

import eg.pharma.api.enums.Gender;

public record UserResource(
        Long id,
        String fullName,
        String username,
        String firstName,
        String lastName,
        String phone,
        String email,
        Gender gender,
        String address,
        String city,
        String role,
        boolean isDeleted
) {
}
