package eg.pharma.api.features.medicine;

import eg.pharma.api.exception.BusinessException;
import eg.pharma.api.features.medicine.dto.MedicineMapper;
import eg.pharma.api.features.medicine.dto.MedicineRequest;
import eg.pharma.api.features.medicine.dto.MedicineResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            throw new BusinessException("Medicine not found", HttpStatus.NOT_FOUND);
        }
        return medicine;
    }

    public MedicineResource getMedicineById(Long id) {
        Medicine medicine = findMedicineById(id);
        return medicineMapper.toResource(medicine);
    }

    public List<MedicineResource> getAllMedicines(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Medicine> medicines = medicineRepository.findAll(pageable);
        return medicineMapper.toResourceList(medicines.getContent());
    }

    @Transactional
    public MedicineResource createMedicine(MedicineRequest medicineRequest) {
        Medicine medicine = medicineMapper.toEntity(medicineRequest);
        medicineRepository.save(medicine);
        return medicineMapper.toResource(medicine);
    }

    @Transactional
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
