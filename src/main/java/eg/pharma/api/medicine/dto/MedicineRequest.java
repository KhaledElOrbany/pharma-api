package eg.pharma.api.medicine.dto;

import eg.pharma.enums.MedicineType;

public class MedicineRequest {
    private String name;
    private String activeIngredient;
    private String description;
    private String barcode;
    private Double price;
    private MedicineType type;
    private String notes;

    public String getName() {
        return name;
    }

    public String getActiveIngredient() {
        return activeIngredient;
    }

    public String getDescription() {
        return description;
    }

    public String getBarcode() {
        return barcode;
    }

    public Double getPrice() {
        return price;
    }

    public MedicineType getType() {
        return type;
    }

    public String getNotes() {
        return notes;
    }
}
