package eg.pharma.api.address.governorate;

import org.springframework.stereotype.Service;

@Service
public class GovernorateService {

    private final GovernorateRepository governorateRepository;

    public GovernorateService(GovernorateRepository governorateRepository) {
        this.governorateRepository = governorateRepository;
    }
}
