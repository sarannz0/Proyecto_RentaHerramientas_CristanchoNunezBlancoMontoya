package com.bkseducate.securityapp.domain.ports;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.bkseducate.securityapp.domain.model.Country;

public interface CountryRepositoryPort {

    Country save(Country country);

    Optional<Country> findById(UUID id);

    List<Country> findAll();

    Optional<Country> findByIsocode(String isocode);
}
