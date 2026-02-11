package com.bkseducate.securityapp.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class ReporteDano {
    private UUID id;
    private String description;
    private double repairCost;
    
    private LocalDateTime date;
    private Rent rent;

    private ReporteDano() {
    }

    public static ReporteDano create(
        String description,
        double repairCost,
        Rent rent
    ) {
        ReporteDano report = new ReporteDano();
        report.id = UUID.randomUUID();
        report.description = description;
        report.repairCost = repairCost;
        report.date = LocalDateTime.now();
        report.rent = rent;

        return report;
    }

    public static ReporteDano recreate(
        UUID id,
        String description,
        double repairCost,
        LocalDateTime date,
        Rent rent
    ) {
        ReporteDano report = new  ReporteDano();
        report.id = id;
        report.description = description;
        report.repairCost = repairCost;
        report.date = date;
        report.rent = rent;

        return report;
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public double getRepairCost() {
        return repairCost;
    }

    public Rent getRent() {
        return rent;
    }

    public void setRent(Rent rent) {
        this.rent = rent;
    }
}
