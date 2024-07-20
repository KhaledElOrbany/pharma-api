package eg.pharma.api.features.medicine.dto;

import eg.pharma.api.interfaces.IMapper;
import eg.pharma.api.features.medicine.Medicine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineMapper implements IMapper<Medicine, MedicineResource, MedicineRequest> {
    @Override
    public List<MedicineResource> toResourceList(List<Medicine> entities) {
        return entities.stream().map(this::toResource).toList();
    }

    @Override
    public MedicineResource toResource(Medicine entity) {
        return new MedicineResource(
                entity.getId(),
                entity.getName(),
                entity.getActiveIngredient(),
                entity.getDescription(),
                entity.getBarcode(),
                entity.getPrice(),
                entity.getType(),
                entity.getNotes()
        );
    }

    @Override
    public Medicine toEntity(MedicineRequest request) {
        return new Medicine(
                request.getName(),
                request.getActiveIngredient(),
                request.getDescription(),
                request.getBarcode(),
                request.getPrice(),
                request.getType(),
                request.getNotes()
        );
    }

    @Override
    public Medicine updateEntity(Medicine entity, MedicineRequest request) {
        entity.setName(request.getName());
        entity.setActiveIngredient(request.getActiveIngredient());
        entity.setDescription(request.getDescription());
        entity.setBarcode(request.getBarcode());
        entity.setPrice(request.getPrice());
        entity.setType(request.getType());
        entity.setNotes(request.getNotes());
        return entity;
    }
}
