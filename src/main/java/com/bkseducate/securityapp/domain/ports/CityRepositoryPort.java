package com.bkseducate.securityapp.domain.ports;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.bkseducate.securityapp.domain.model.City;

public interface CityRepositoryPort {
    City save(City city);

    Optional<City> findById(UUID id);

    List<City> findAll();

    Optional<City> findByName(String name);

}
