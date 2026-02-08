package com.bkseducate.securityapp.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Rent {

    private UUID id;
    private UUID customerId;
    private UUID toolItemId;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private RentStatus status;

    private Rent() {
    }

    public static Rent create(
        UUID customerId,
        UUID toolItemId,
        LocalDateTime startDate,
        LocalDateTime endDate
    ) {
        Rent rent = new Rent();
        rent.id = UUID.randomUUID();
        rent.customerId = customerId;
        rent.toolItemId = toolItemId;
        rent.startDate = startDate;
        rent.endDate = endDate;
        rent.status = RentStatus.RENTED;

        return rent;
    }

    public static Rent recreate(
        UUID id,
        UUID customerId,
        UUID toolItemId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        RentStatus status
    ) {
        Rent rent = new Rent();
        rent.id = id;
        rent.customerId = customerId;
        rent.toolItemId = toolItemId;
        rent.startDate = startDate;
        rent.endDate = endDate;
        rent.status = status;

        return rent;
    }

    public UUID getId() {
        return id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public UUID getToolItemId() {
        return toolItemId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public RentStatus getStatus() {
        return status;
    }
}

