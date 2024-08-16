package eg.pharma.api.features.address.city;

import eg.pharma.api.exception.BusinessException;
import eg.pharma.api.features.address.governorate.Governorate;
import eg.pharma.api.features.address.governorate.GovernorateService;
import org.springframework.stereotype.Service;
import eg.pharma.api.features.address.city.dto.CityMapper;
import eg.pharma.api.features.address.city.dto.CityRequest;
import eg.pharma.api.features.address.city.dto.CityResource;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;
    private final GovernorateService governorateService;

    public CityService(CityRepository cityRepository, CityMapper cityMapper, GovernorateService governorateService) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
        this.governorateService = governorateService;
    }

    private City findCityById(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new BusinessException("City not found", HttpStatus.NOT_FOUND));
    }

    public CityResource getCityById(Long id) {
        City city = findCityById(id);
        return cityMapper.toResource(city);
    }

    public List<CityResource> getAllCities(Long governorateId) {
        Governorate governorate = governorateService.findGovernorateById(governorateId);
        List<City> cities = cityRepository.findAllByGovernorate(governorate);
        return cityMapper.toResourceList(cities);
    }

    @Transactional
    public CityResource createCity(CityRequest cityRequest) {
        City city = cityMapper.toEntity(cityRequest);
        city = cityRepository.save(city);
        return cityMapper.toResource(city);
    }

    @Transactional
    public CityResource updateCity(Long id, CityRequest cityRequest) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new BusinessException("City not found", HttpStatus.NOT_FOUND));
        cityMapper.updateEntity(city, cityRequest);
        city = cityRepository.save(city);
        return cityMapper.toResource(city);
    }

    public void deleteCity(Long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new BusinessException("City not found", HttpStatus.NOT_FOUND));
        cityRepository.delete(city);
    }
}
