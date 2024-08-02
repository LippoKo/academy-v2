package com.ctw.workstation.shared;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
public abstract class EntityBase {
    @Id
    @GeneratedValue( strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "CREATED_AT", nullable = false )
    private LocalDateTime created_at;

    @Column(name = "MODIFIED_AT", nullable = false)
    private LocalDateTime modified_at;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getModified_at() {
        return modified_at;
    }

    public void setModified_at(LocalDateTime modified_at) {
        this.modified_at = modified_at;
    }

    @PrePersist
    protected void onCreate() {
        this.created_at = LocalDateTime.now();
        this.modified_at = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.modified_at = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof EntityBase that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
