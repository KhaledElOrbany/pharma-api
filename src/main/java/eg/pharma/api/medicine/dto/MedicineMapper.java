package eg.pharma.api.medicine.dto;

import eg.pharma.api.config.interfaces.IMapper;
import eg.pharma.api.medicine.Medicine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineMapper implements IMapper<Medicine, MedicineResource, MedicineRequest> {
    @Override
    public List<MedicineResource> toResourceList(List<Medicine> entities) {
        return List.of();
    }

    @Override
    public MedicineResource toResource(Medicine entity) {
        return null;
    }

    @Override
    public Medicine toEntity(MedicineRequest request) {
        return null;
    }

    @Override
    public Medicine updateEntity(Medicine entity, MedicineRequest request) {
        return null;
    }
}
