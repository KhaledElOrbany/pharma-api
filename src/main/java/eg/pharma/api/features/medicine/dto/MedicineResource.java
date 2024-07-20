package eg.pharma.api.features.medicine.dto;

import eg.pharma.api.enums.MedicineType;

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
