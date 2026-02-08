package com.bkseducate.securityapp.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class RentRequest {

    private UUID id;
    private UUID rentId;
    private LocalDateTime createdAt;
    private RentRequestStatus status;

    private RentRequest() {
    }

    public static RentRequest create(UUID rentId) {
        RentRequest request = new RentRequest();
        request.id = UUID.randomUUID();
        request.rentId = rentId;
        request.createdAt = LocalDateTime.now();
        request.status = RentRequestStatus.ACCEPTED;
        return request;
    }

    public static RentRequest recreate(
        UUID id,
        UUID rentId,
        LocalDateTime createdAt,
        RentRequestStatus status
    ) {
        RentRequest request = new RentRequest();
        request.id = id;
        request.rentId = rentId;
        request.createdAt = createdAt;
        request.status = status;
        return request;
    }

    public UUID getId() {
        return id;
    }

    public UUID getRentId() {
        return rentId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public RentRequestStatus getStatus() {
        return status;
    }
}
