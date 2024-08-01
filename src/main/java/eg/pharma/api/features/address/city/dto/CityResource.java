package eg.pharma.api.features.address.city.dto;

import eg.pharma.api.features.address.governorate.dto.GovernorateResource;

public record CityResource(
        Long id,
        String nameAr,
        String nameEN,
        GovernorateResource governorate
) {
}
