package com.bkseducate.securityapp.infrastructure.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bkseducate.securityapp.infrastructure.persistence.entity.ToolCatalogEntity;

@Repository
public interface ToolCatalogJpaRepository extends JpaRepository<ToolCatalogEntity, UUID>{
    
}
