package eg.pharma.api.pharmacy.dto;

public class PharmacyRequest {
    private String name;
    private String owner;
    private String address;
    private String phone;

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}
