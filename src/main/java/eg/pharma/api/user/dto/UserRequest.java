package eg.pharma.api.user.dto;

public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }
}
