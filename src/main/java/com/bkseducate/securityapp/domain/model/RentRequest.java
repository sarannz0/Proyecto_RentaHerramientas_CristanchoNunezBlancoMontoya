package com.bkseducate.securityapp.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class RentRequest {

    private UUID id;
    private Rent rent;
    private LocalDateTime createdAt;
    private RentRequestStatus status;

    private RentRequest() {
    }

    public static RentRequest create(Rent rent) {
        RentRequest request = new RentRequest();
        request.id = UUID.randomUUID();
        request.rent = rent;
        request.createdAt = LocalDateTime.now();
        request.status = RentRequestStatus.PENDING;
        return request;
    }

    public static RentRequest recreate(
        UUID id,
        Rent rent,
        LocalDateTime createdAt,
        RentRequestStatus status
    ) {
        RentRequest request = new RentRequest();
        request.id = id;
        request.rent = rent;
        request.createdAt = createdAt;
        request.status = status;
        return request;
    }

    public UUID getId() {
        return id;
    }

    public Rent getRent() {
        return rent;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public RentRequestStatus getStatus() {
        return status;
    }
}
