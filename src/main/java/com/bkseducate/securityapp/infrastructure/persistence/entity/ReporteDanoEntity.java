package com.bkseducate.securityapp.infrastructure.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reporte_danos")
public class ReporteDanoEntity {
    @Id
    @Column(columnDefinition = "CHAR(36)", length = 36)
    private UUID id;

    @Column(nullable = true)
    private String description;

    @Column(nullable = true)
    private double repairCost;

    @Column(nullable = false)
    private LocalDateTime date;

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private RentEntity rent;

    public ReporteDanoEntity() {
    }

    public ReporteDanoEntity(
        UUID id,
        String description,
        double repairCost,
        LocalDateTime date,
        RentEntity rent
        ) {
        this.id = id;
        this.description = description;
        this.repairCost = repairCost;
        this.date = date;
        this.rent = rent;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRepairCost() {
        return repairCost;
    }

    public void setRepairCost(double repairCost) {
        this.repairCost = repairCost;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }


    public RentEntity getRent() {
        return rent;
    }

    public void setRent(RentEntity rent) {
        this.rent = rent;
    }


}
