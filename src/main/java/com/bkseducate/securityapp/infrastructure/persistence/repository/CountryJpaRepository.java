package com.bkseducate.securityapp.infrastructure.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bkseducate.securityapp.infrastructure.persistence.entity.CountryEntity;
public interface CountryJpaRepository extends JpaRepository<CountryEntity, UUID>{

    @Query("SELECT c FROM CountryEntity c WHERE isocode = :iso")
    Optional<CountryEntity> findByIsocode(@Param("iso") String isocode);
    
} 