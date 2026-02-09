package com.bkseducate.securityapp.infrastructure.persistence.adapters.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bkseducate.securityapp.domain.model.Rent;
import com.bkseducate.securityapp.domain.ports.RentRepository;
import com.bkseducate.securityapp.infrastructure.persistence.mapper.RentMapper;
import com.bkseducate.securityapp.infrastructure.persistence.repository.RentJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RentRepositoryAdapter implements RentRepository {
    
    private final RentJpaRepository jpaRepository;
    private final RentMapper rentMapper;

    @Override
    public List<Rent> findAll() {
        return jpaRepository.findAll().stream().map(rentMapper::toDomain).toList();
    }

    @Override
    public Optional<Rent> findByID(UUID id) {
        return jpaRepository.findById(id).map(rentMapper::toDomain);
    }

    @Override
    public Rent save(Rent rent) {
        return rentMapper.toDomain(jpaRepository.save(rentMapper.toEntity(rent)));
    }

    @Override
    public void delete(UUID id) {
        jpaRepository.deleteById(id);
    }

    

}
