package eg.pharma.audit;

import eg.pharma.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Audit {
    @OneToOne
    private User createdBy;

    @OneToOne
    private User updatedBy;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @PrePersist
    public void onPrePersist() {
        this.setCreatedBy(User.getCurrentUser());
        this.setUpdatedBy(User.getCurrentUser());
        this.setDateCreated(LocalDateTime.now());
        this.setLastUpdated(LocalDateTime.now());
    }

    @PreUpdate
    public void onPreUpdate() {
        this.setUpdatedBy(User.getCurrentUser());
        this.setLastUpdated(LocalDateTime.now());
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
