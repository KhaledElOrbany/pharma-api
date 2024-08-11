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
                governorate.getNameAr()
        );
    }

    @Override
    public Governorate toEntity(GovernorateRequest request) {
        return new Governorate(
                request.getNameAr(),
                request.getNameEn()
        );
    }

    @Override
    public Governorate updateEntity(Governorate governorate, GovernorateRequest request) {
        governorate.setNameAr(request.getNameAr());
        governorate.setNameEn(request.getNameEn());
        return governorate;
    }
}
