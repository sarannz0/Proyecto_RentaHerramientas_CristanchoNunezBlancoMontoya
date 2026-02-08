package com.bkseducate.securityapp.infrastructure.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.bkseducate.securityapp.domain.model.RentRequestStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rent_requests")
public class RentRequestEntity {
    
    @Id
    @Column(columnDefinition = "CHAR(36)", length = 36)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rent_id", nullable = false, updatable = false)
    private RentEntity rent;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RentRequestStatus status;


    public RentRequestEntity(UUID id, RentEntity rent, LocalDateTime createdAt, RentRequestStatus status) {
        this.id = id;
        this.rent = rent;
        this.createdAt = createdAt;
        this.status = status;
    }

    public RentRequestEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public RentEntity getRent() {
        return rent;
    }

    public void setRent(RentEntity rent) {
        this.rent = rent;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public RentRequestStatus getStatus() {
        return status;
    }

    public void setStatus(RentRequestStatus status) {
        this.status = status;
    }

}
