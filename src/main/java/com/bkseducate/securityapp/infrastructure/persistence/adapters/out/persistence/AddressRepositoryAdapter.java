package com.bkseducate.securityapp.infrastructure.persistence.adapters.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.bkseducate.securityapp.application.mapper.AddressMapper;
import com.bkseducate.securityapp.domain.model.Address;
import com.bkseducate.securityapp.domain.ports.AddressRepositoryPort;
import com.bkseducate.securityapp.infrastructure.persistence.repository.AddressJpaRepository;

@Component
public class AddressRepositoryAdapter implements AddressRepositoryPort{
    private final AddressJpaRepository jpaRepository;
    private final AddressMapper addressMapper;

    public AddressRepositoryAdapter(
        AddressJpaRepository jpaRepository,
        AddressMapper addressMapper
    ) {
        this.jpaRepository = jpaRepository;
        this.addressMapper = addressMapper;
    }

    @Override
    public Address save(Address address) {
        return addressMapper.toDomain(jpaRepository.save(addressMapper.toEntity(address)));
    }

    @Override
    public Optional<Address> findById(UUID id) {
       return jpaRepository.findById(id).map(addressMapper::toDomain);
    }

    @Override
    public List<Address> findAll() {
        return jpaRepository.findAll().stream().map(addressMapper::toDomain).toList();
    }
}
