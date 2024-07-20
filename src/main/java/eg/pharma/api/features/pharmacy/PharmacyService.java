package eg.pharma.api.features.pharmacy;

import eg.pharma.api.features.pharmacy.dto.PharmacyMapper;
import eg.pharma.api.features.pharmacy.dto.PharmacyRequest;
import eg.pharma.api.features.pharmacy.dto.PharmacyResource;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PharmacyService {

    private final PharmacyMapper pharmacyMapper;
    private final PharmacyRepository pharmacyRepository;

    public PharmacyService(PharmacyMapper pharmacyMapper, PharmacyRepository pharmacyRepository) {
        this.pharmacyMapper = pharmacyMapper;
        this.pharmacyRepository = pharmacyRepository;
    }

    public PharmacyResource getPharmacyById(Long id) {
        Pharmacy pharmacy = pharmacyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pharmacy not found"));
        return pharmacyMapper.toResource(pharmacy);
    }

    public List<PharmacyResource> getAllPharmacies() {
        List<Pharmacy> pharmacies = pharmacyRepository.findAll();
        return pharmacyMapper.toResourceList(pharmacies);
    }

    public PharmacyResource createPharmacy(PharmacyRequest pharmacyRequest) {
        Pharmacy pharmacy = pharmacyMapper.toEntity(pharmacyRequest);
        pharmacy = pharmacyRepository.save(pharmacy);
        return pharmacyMapper.toResource(pharmacy);
    }

    public PharmacyResource updatePharmacy(Long id, PharmacyRequest pharmacyRequest) {
        Pharmacy pharmacy = pharmacyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pharmacy not found"));
        pharmacy = pharmacyMapper.updateEntity(pharmacy, pharmacyRequest);
        pharmacy = pharmacyRepository.save(pharmacy);
        return pharmacyMapper.toResource(pharmacy);
    }

    public void deletePharmacy(Long id) {
        pharmacyRepository.deleteById(id);
    }
}
