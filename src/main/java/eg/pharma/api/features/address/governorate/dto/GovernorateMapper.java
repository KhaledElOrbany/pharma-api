package eg.pharma.api.features.address.governorate.dto;

import eg.pharma.api.features.address.governorate.Governorate;
import eg.pharma.api.interfaces.IMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GovernorateMapper implements IMapper<Governorate, GovernorateResource, GovernorateRequest> {
    @Override
    public List<GovernorateResource> toResourceList(List<Governorate> cities) {
        return cities.stream().map(this::toResource).toList();
    }

    @Override
    public GovernorateResource toResource(Governorate governorate) {
        return new GovernorateResource(
                governorate.getId(),
                governorate.getName()
        );
    }

    @Override
    public Governorate toEntity(GovernorateRequest request) {
        return new Governorate(
                request.getName()
        );
    }

    @Override
    public Governorate updateEntity(Governorate governorate, GovernorateRequest request) {
        governorate.setName(request.getName());
        return governorate;
    }
}
