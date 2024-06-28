package eg.pharma.api.medicine;

import eg.pharma.enums.MedicineType;
import eg.pharma.api.audit.Audit;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;

@Entity
@Table(name = "medicine")
@SQLDelete(sql = "UPDATE medicine SET is_deleted = true WHERE id = ?")
public class Medicine extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String activeIngredient;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String barcode;

    @Column(nullable = false)
    private double price;

    @Enumerated(EnumType.STRING)
    private MedicineType type;

    private String notes;

    Boolean isDeleted = Boolean.FALSE;

    public Medicine() {
    }

    public Medicine(String name, String activeIngredient, String description, String barcode, double price, MedicineType type, String notes) {
        this.name = name;
        this.activeIngredient = activeIngredient;
        this.description = description;
        this.barcode = barcode;
        this.price = price;
        this.type = type;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActiveIngredient() {
        return activeIngredient;
    }

    public void setActiveIngredient(String activeIngredient) {
        this.activeIngredient = activeIngredient;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public MedicineType getType() {
        return type;
    }

    public void setType(MedicineType type) {
        this.type = type;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
