package com.bkseducate.securityapp.domain.ports;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.bkseducate.securityapp.domain.model.RentRequest;

public interface RentRequestRepository {
    List<RentRequest> findAll();
    Optional<RentRequest> findById(UUID id);
    RentRequest save(RentRequest rentRequest);
    void delete(UUID id);
}
