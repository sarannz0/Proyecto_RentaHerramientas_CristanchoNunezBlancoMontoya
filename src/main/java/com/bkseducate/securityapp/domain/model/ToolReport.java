package com.bkseducate.securityapp.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class ToolReport {

    private UUID id;
    private UUID toolItemId;
    private UUID userId;
    private UUID rentId;
    private String description;
    private LocalDateTime date;

    private ToolReport() {
    }

    public static ToolReport create(
        UUID toolItemId,
        UUID userId,
        UUID rentId,
        String description
    ) {
        ToolReport report = new ToolReport();
        report.id = UUID.randomUUID();
        report.toolItemId = toolItemId;
        report.userId = userId;
        report.rentId = rentId;
        report.description = description;
        report.date = LocalDateTime.now();

        return report;
    }

    public static ToolReport recreate(
        UUID id,
        UUID toolItemId,
        UUID userId,
        UUID rentId,
        String description,
        LocalDateTime date
    ) {
        ToolReport report = new ToolReport();
        report.id = id;
        report.toolItemId = toolItemId;
        report.userId = userId;
        report.rentId = rentId;
        report.description = description;
        report.date = date;

        return report;
    }

    public UUID getId() {
        return id;
    }

    public UUID getToolItemId() {
        return toolItemId;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getRentId() {
        return rentId;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
