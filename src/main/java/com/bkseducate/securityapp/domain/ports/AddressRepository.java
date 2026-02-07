package com.bkseducate.securityapp.domain.ports;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.bkseducate.securityapp.domain.model.Address;

public interface AddressRepository {
    Address save(Address Address);

    Optional<Address> findById(UUID id);

    List<Address> findAll();

    Address update(UUID id, Address address);
}
