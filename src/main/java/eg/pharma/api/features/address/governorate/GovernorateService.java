package eg.pharma.api.features.address.governorate;

import eg.pharma.api.exception.BusinessException;
import eg.pharma.api.features.address.governorate.dto.GovernorateMapper;
import eg.pharma.api.features.address.governorate.dto.GovernorateRequest;
import eg.pharma.api.features.address.governorate.dto.GovernorateResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GovernorateService {

    private final GovernorateRepository governorateRepository;
    private final GovernorateMapper governorateMapper;

    public GovernorateService(GovernorateRepository governorateRepository, GovernorateMapper governorateMapper) {
        this.governorateRepository = governorateRepository;
        this.governorateMapper = governorateMapper;
    }

    public Governorate findGovernorateById(Long id) {
        return governorateRepository.findById(id)
                .orElseThrow(() -> new BusinessException("User not found", HttpStatus.NOT_FOUND));
    }

    public GovernorateResource getGovernorateById(Long id) {
        Governorate governorate = findGovernorateById(id);
        return governorateMapper.toResource(governorate);
    }

    public List<GovernorateResource> getAllGovernorates() {
        List<Governorate> governorates = governorateRepository.findAll();
        return governorateMapper.toResourceList(governorates);
    }

    public GovernorateResource createGovernorate(GovernorateRequest governorateRequest) {
        Governorate governorate = governorateMapper.toEntity(governorateRequest);
        governorate = governorateRepository.save(governorate);
        return governorateMapper.toResource(governorate);
    }

    public GovernorateResource updateGovernorate(Long id, GovernorateRequest governorateRequest) {
        Governorate governorate = governorateRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Governorate not found", HttpStatus.NOT_FOUND));
        governorateMapper.updateEntity(governorate, governorateRequest);
        governorate = governorateRepository.save(governorate);
        return governorateMapper.toResource(governorate);
    }

    public void deleteGovernorate(Long id) {
        Governorate governorate = governorateRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Governorate not found", HttpStatus.NOT_FOUND));
        governorateRepository.delete(governorate);
    }
}
