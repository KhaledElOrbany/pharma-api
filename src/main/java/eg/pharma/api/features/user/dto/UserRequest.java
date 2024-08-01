package eg.pharma.api.features.user.dto;

import eg.pharma.api.enums.Gender;
import eg.pharma.api.features.address.city.City;
import eg.pharma.api.features.role.Role;

public class UserRequest {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String phone;
    private String email;
    private Gender gender;
    private Role role;
    private String district;
    private City city;

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Gender getGender() {
        return gender;
    }

    public Role getRole() {
        return role;
    }

    public String getDistrict() {
        return district;
    }

    public City getCity() {
        return city;
    }
}
