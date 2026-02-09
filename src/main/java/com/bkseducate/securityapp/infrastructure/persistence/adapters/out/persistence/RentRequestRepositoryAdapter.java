package com.bkseducate.securityapp.infrastructure.persistence.adapters.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.bkseducate.securityapp.domain.model.RentRequest;
import com.bkseducate.securityapp.domain.ports.RentRepository;
import com.bkseducate.securityapp.domain.ports.RentRequestRepository;
import com.bkseducate.securityapp.infrastructure.persistence.mapper.RentRequestMapper;
import com.bkseducate.securityapp.infrastructure.persistence.repository.RentRequestJpaRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RentRequestRepositoryAdapter implements RentRequestRepository{
    private final RentRequestJpaRepository jpaRepository;
    private final RentRequestMapper rentRequestMapper;

    @Override
    public List<RentRequest> findAll() {
        return jpaRepository.findAll().stream().map(rentRequestMapper::toDomain).toList();
    }

    @Override
    public Optional<RentRequest> findById(UUID id) {
        return jpaRepository.findById(id).map(rentRequestMapper::toDomain);
    }
    @Override
    public RentRequest save(RentRequest rentRequest) {
        return rentRequestMapper.toDomain(jpaRepository.save(rentRequestMapper.toEntity(rentRequest)));
    }
    @Override
    public void delete(UUID id) {
        jpaRepository.deleteById(id);
    }
}
