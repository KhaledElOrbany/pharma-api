package eg.pharma.api.features.pharmacy;

import eg.pharma.api.exception.BusinessException;
import eg.pharma.api.features.pharmacy.dto.PharmacyMapper;
import eg.pharma.api.features.pharmacy.dto.PharmacyRequest;
import eg.pharma.api.features.pharmacy.dto.PharmacyResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .orElseThrow(() -> new BusinessException("Pharmacy not found", HttpStatus.NOT_FOUND));
        return pharmacyMapper.toResource(pharmacy);
    }

    public List<PharmacyResource> getAllPharmacies(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Pharmacy> pharmacies = pharmacyRepository.findAll(pageable);
        return pharmacyMapper.toResourceList(pharmacies.getContent());
    }

    @Transactional
    public PharmacyResource createPharmacy(PharmacyRequest pharmacyRequest) {
        Pharmacy pharmacy = pharmacyMapper.toEntity(pharmacyRequest);
        pharmacy = pharmacyRepository.save(pharmacy);
        return pharmacyMapper.toResource(pharmacy);
    }

    @Transactional
    public PharmacyResource updatePharmacy(Long id, PharmacyRequest pharmacyRequest) {
        Pharmacy pharmacy = pharmacyRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Pharmacy not found", HttpStatus.NOT_FOUND));
        pharmacy = pharmacyMapper.updateEntity(pharmacy, pharmacyRequest);
        pharmacy = pharmacyRepository.save(pharmacy);
        return pharmacyMapper.toResource(pharmacy);
    }

    public void deletePharmacy(Long id) {
        pharmacyRepository.deleteById(id);
    }
}
