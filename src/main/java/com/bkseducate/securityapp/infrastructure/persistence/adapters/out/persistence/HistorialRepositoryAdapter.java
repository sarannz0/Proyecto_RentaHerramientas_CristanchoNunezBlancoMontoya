package com.bkseducate.securityapp.infrastructure.persistence.adapters.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.bkseducate.securityapp.domain.model.HistorialPago;
import com.bkseducate.securityapp.domain.ports.HistorialRepository;
import com.bkseducate.securityapp.infrastructure.persistence.mapper.HistorialMapper;
import com.bkseducate.securityapp.infrastructure.persistence.repository.HistorialJpaRepository;

public class HistorialRepositoryAdapter implements HistorialRepository {
    
    private HistorialJpaRepository jpaRepository;
    private HistorialMapper historialMapper;
    @Override
    public List<HistorialPago> findAll() {
        return jpaRepository.findAll().stream().map(historialMapper::toDomain).toList();
    }
    @Override
    public Optional<HistorialPago> findById(UUID id) {
        return jpaRepository.findById(id).map(historialMapper::toDomain);
    }

    
}
