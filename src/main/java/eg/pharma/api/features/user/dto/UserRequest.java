package eg.pharma.api.features.user.dto;

import eg.pharma.api.features.role.Role;

public class UserRequest {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private Role role;

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}
