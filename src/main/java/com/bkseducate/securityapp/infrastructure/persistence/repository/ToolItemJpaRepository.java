package com.bkseducate.securityapp.infrastructure.persistence.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bkseducate.securityapp.infrastructure.persistence.entity.ToolItemEntity;

@Repository
public interface ToolItemJpaRepository extends JpaRepository<ToolItemEntity, UUID>{

    List<ToolItemEntity> findAllByToolCatalogEntityId(UUID toolCatalogEntityId);
}
