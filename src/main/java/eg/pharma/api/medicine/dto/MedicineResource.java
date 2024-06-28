package eg.pharma.api.medicine.dto;

import eg.pharma.enums.MedicineType;

public record MedicineResource(
        Long id,
        String name,
        String activeIngredient,
        String description,
        String barcode,
        double price,
        MedicineType type,
        String notes
) {
}
