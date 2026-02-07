package com.bkseducate.securityapp.infrastructure.persistence.adapters.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.bkseducate.securityapp.domain.exceptions.UserNotFoundException;
import com.bkseducate.securityapp.domain.model.Address;
import com.bkseducate.securityapp.domain.ports.AddressRepository;
import com.bkseducate.securityapp.infrastructure.persistence.entity.AddressEntity;
import com.bkseducate.securityapp.infrastructure.persistence.mapper.AddressMapper;
import com.bkseducate.securityapp.infrastructure.persistence.mapper.CityMapper;
import com.bkseducate.securityapp.infrastructure.persistence.repository.AddressJpaRepository;

@Component
public class AddressRepositoryAdapter implements AddressRepository{
    private final AddressJpaRepository jpaRepository;
    private final AddressMapper addressMapper;
    private final CityMapper cityMapper;
    public AddressRepositoryAdapter(
        AddressJpaRepository jpaRepository,
        @Lazy AddressMapper addressMapper,
        @Lazy CityMapper cityMapper
    ) {
        this.cityMapper = cityMapper;
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

    @Override
    public Address update(UUID id, Address address) {
        AddressEntity entity = jpaRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("No se pudo encontrar el usuario con ID " + id));
        entity.setAddress(address.getAddress());
        entity.setPostal_code(address.getPostalCode());
        entity.setCity(cityMapper.toEntity(address.getCity()));
        return addressMapper.toDomain(jpaRepository.save(entity));
    }
}
