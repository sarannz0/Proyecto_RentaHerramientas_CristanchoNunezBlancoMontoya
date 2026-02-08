package com.bkseducate.securityapp.infrastructure.persistence.adapters.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.bkseducate.securityapp.application.dto.Supplier.SupplierRequest;
import com.bkseducate.securityapp.domain.exceptions.UserNotFoundException;
import com.bkseducate.securityapp.domain.model.SupplierM;
import com.bkseducate.securityapp.domain.ports.SupplierRepository;
import com.bkseducate.securityapp.infrastructure.persistence.entity.SupplierEntity;
import com.bkseducate.securityapp.infrastructure.persistence.mapper.AddressMapper;
import com.bkseducate.securityapp.infrastructure.persistence.mapper.SupplierMapper;
import com.bkseducate.securityapp.infrastructure.persistence.repository.SupplierJpaRepository;

@Component
public class SupplierRepositoryAdapter implements SupplierRepository{

    private final SupplierJpaRepository jpaRepository;
    private final SupplierMapper supplierMapper;

    public SupplierRepositoryAdapter(
        SupplierJpaRepository jpaRepository,
        SupplierMapper supplierMapper,
        AddressMapper addressMapper
    ) {
        this.supplierMapper = supplierMapper;
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<SupplierM> findAll() {
        return jpaRepository.findAll().stream().map(supplierMapper::toDomain).toList();
    }

    @Override
    public Optional<SupplierM> findById(UUID userId) {
        return jpaRepository.findById(userId).map(supplierMapper::toDomain);
    }

    @Override
    public SupplierM save(SupplierM supplier) {
        return supplierMapper.toDomain(jpaRepository.save(supplierMapper.toEntity(supplier)));
    }

    @Override
    public SupplierM update(UUID userId, SupplierRequest request) {
        SupplierEntity entity = jpaRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("No se pudo encontrar el usuario con ID "+userId));
        entity.setCompanyName(request.companyName());
        //entity.setAddres(addressMapper.toEntity(request.addressDesc()));
        return supplierMapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public void delete(UUID userId) {
        jpaRepository.deleteById(userId);
    }    
}
