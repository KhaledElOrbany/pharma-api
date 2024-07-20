package eg.pharma.api.features.medicine;

import eg.pharma.api.features.medicine.dto.MedicineMapper;
import eg.pharma.api.features.medicine.dto.MedicineRequest;
import eg.pharma.api.features.medicine.dto.MedicineResource;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {

    private final MedicineMapper medicineMapper;
    private final MedicineRepository medicineRepository;

    public MedicineService(MedicineMapper medicineMapper, MedicineRepository medicineRepository) {
        this.medicineMapper = medicineMapper;
        this.medicineRepository = medicineRepository;
    }

    public Medicine findMedicineById(Long id) {
        Medicine medicine = medicineRepository.findByIdAndIsDeleted(id, false);
        if (medicine == null) {
            throw new EntityNotFoundException("Medicine not found");
        }
        return medicine;
    }

    public MedicineResource getMedicineById(Long id) {
        Medicine medicine = findMedicineById(id);
        return medicineMapper.toResource(medicine);
    }

    public List<MedicineResource> getAllMedicines() {
        List<Medicine> medicines = medicineRepository.findAll();
        return medicineMapper.toResourceList(medicines);
    }

    public MedicineResource createMedicine(MedicineRequest medicineRequest) {
        Medicine medicine = medicineMapper.toEntity(medicineRequest);
        medicineRepository.save(medicine);
        return medicineMapper.toResource(medicine);
    }

    public MedicineResource updateMedicine(Long id, MedicineRequest medicineRequest) {
        Medicine medicine = findMedicineById(id);
        medicine = medicineMapper.updateEntity(medicine, medicineRequest);
        medicineRepository.save(medicine);
        return medicineMapper.toResource(medicine);
    }

    public void deleteMedicine(Long id) {
        Medicine medicine = findMedicineById(id);
        medicineRepository.delete(medicine);
    }
}
