package eg.pharma.api.features.address.city;

import eg.pharma.api.base.ApiResponse;
import eg.pharma.api.base.BaseController;
import eg.pharma.api.features.address.city.dto.CityRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/city")
public class CityController extends BaseController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/{id}")
    public ApiResponse getCityById(@PathVariable("id") Long id) {
        return respond(cityService.getCityById(id));
    }

    @GetMapping(path = "/list")
    public ApiResponse getAllCities(@RequestParam Long governorateId) {
        List<?> data = cityService.getAllCities(governorateId);
        return respond(data, new HashMap<>() {{
            put("total", data.size());
        }});
    }

    @PostMapping("/create")
    public ApiResponse createCity(@RequestBody CityRequest cityRequest) {
        return respond(cityService.createCity(cityRequest));
    }

    @PutMapping(path = "/update/{id}")
    public ApiResponse updateCity(@PathVariable("id") Long id, @RequestBody CityRequest cityRequest) {
        return respond(cityService.updateCity(id, cityRequest));
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteCity(@PathVariable("id") Long id) {
        cityService.deleteCity(id);
        return respond();
    }
}
