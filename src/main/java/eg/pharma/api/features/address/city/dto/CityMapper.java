package eg.pharma.api.features.address.city.dto;

import eg.pharma.api.features.address.city.City;
import eg.pharma.api.features.address.governorate.dto.GovernorateMapper;
import eg.pharma.api.interfaces.IMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityMapper implements IMapper<City, CityResource, CityRequest> {

    private final GovernorateMapper governorateMapper;

    public CityMapper(@Lazy GovernorateMapper governorateMapper) {
        this.governorateMapper = governorateMapper;
    }

    @Override
    public List<CityResource> toResourceList(List<City> cities) {
        return cities.stream().map(this::toResource).toList();
    }

    @Override
    public CityResource toResource(City city) {
        return new CityResource(
                city.getId(),
                city.getNameAr(),
                governorateMapper.toResource(city.getGovernorate())
        );
    }

    @Override
    public City toEntity(CityRequest request) {
        return new City(
                request.getNameAr(),
                request.getNameEn()
        );
    }

    @Override
    public City updateEntity(City city, CityRequest request) {
        city.setNameAr(request.getNameAr());
        city.setNameEn(request.getNameEn());
        return city;
    }
}
