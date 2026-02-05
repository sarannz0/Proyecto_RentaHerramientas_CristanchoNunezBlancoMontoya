package com.bkseducate.securityapp.infrastructure.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bkseducate.securityapp.infrastructure.persistence.entity.CityEntity;

public interface CityJpaRepository extends JpaRepository<CityEntity, UUID> {
    @Query("SELECT ci FROM CityEntity ci WHERE name = :name")
    Optional<CityEntity> findByName(@Param("name") String name);
}

