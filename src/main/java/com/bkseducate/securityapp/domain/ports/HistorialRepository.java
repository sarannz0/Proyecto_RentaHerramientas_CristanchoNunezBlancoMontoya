package com.bkseducate.securityapp.domain.ports;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.bkseducate.securityapp.domain.model.HistorialPago;

public interface HistorialRepository {
    List<HistorialPago> findAll();
    Optional<HistorialPago> findById(UUID id);
} 
