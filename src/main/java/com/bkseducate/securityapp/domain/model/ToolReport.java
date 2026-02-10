package com.bkseducate.securityapp.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class ToolReport {

    private UUID id;
    private ToolItem toolItem;
    private User user;
    private Rent rent;
    private String description;
    private LocalDateTime date;

    private ToolReport() {
    }

    public static ToolReport create(
        ToolItem toolItem,
        User user,
        Rent rent,
        String description
    ) {
        ToolReport report = new ToolReport();
        report.id = UUID.randomUUID();
        report.toolItem = toolItem;
        report.user = user;
        report.rent = rent;
        report.description = description;
        report.date = LocalDateTime.now();

        return report;
    }

    public static ToolReport recreate(
        UUID id,
        ToolItem toolItem,
        User user,
        Rent rent,
        String description,
        LocalDateTime date
    ) {
        ToolReport report = new ToolReport();
        report.id = id;
        report.toolItem = toolItem;
        report.user = user;
        report.rent = rent;
        report.description = description;
        report.date = date;

        return report;
    }

    public UUID getId() {
        return id;
    }

    public ToolItem getToolItem() {
        return toolItem;
    }

    public User getUserId() {
        return user;
    }

    public Rent getRentId() {
        return rent;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
