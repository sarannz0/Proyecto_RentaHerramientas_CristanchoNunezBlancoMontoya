package com.bkseducate.securityapp.infrastructure.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tools_reports")
public class ReporteDanoEntity {
    @Id
    @Column(columnDefinition = "CHAR(36)", length = 36)
    private UUID id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double repairCost;

    @Column(nullable = false)
    private LocalDateTime date;

    public ReporteDanoEntity() {
    }

    public ReporteDanoEntity(
        UUID id,
        String description,
        double repairCost,
        LocalDateTime date
        ) {
        this.id = id;
        this.description = description;
        this.repairCost = repairCost;
        this.date = date;
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


}
