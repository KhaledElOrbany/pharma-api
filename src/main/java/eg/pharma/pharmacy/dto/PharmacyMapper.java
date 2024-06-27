package eg.pharma.pharmacy.dto;

import eg.pharma.pharmacy.Pharmacy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PharmacyMapper {

    public List<PharmacyResource> toResourceList(List<Pharmacy> pharmacies) {
        return pharmacies.stream().map(this::toResource).toList();
    }

    public PharmacyResource toResource(Pharmacy pharmacy) {
        return new PharmacyResource(
                pharmacy.getId(),
                pharmacy.getName(),
                pharmacy.getOwner(),
                pharmacy.getAddress(),
                pharmacy.getPhone()
        );
    }

    public Pharmacy toEntity(PharmacyRequest pharmacyRequest) {
        return new Pharmacy(
                pharmacyRequest.getName(),
                pharmacyRequest.getOwner(),
                pharmacyRequest.getAddress(),
                pharmacyRequest.getPhone()
        );
    }

    public Pharmacy updateEntity(Pharmacy pharmacy, PharmacyRequest pharmacyRequest) {
        pharmacy.setName(pharmacyRequest.getName());
        pharmacy.setOwner(pharmacyRequest.getOwner());
        pharmacy.setAddress(pharmacyRequest.getAddress());
        pharmacy.setPhone(pharmacyRequest.getPhone());
        return pharmacy;
    }
}
