package com.bkseducate.securityapp.infrastructure.persistence.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.bkseducate.securityapp.domain.model.ToolItemStatus;
import com.bkseducate.securityapp.infrastructure.persistence.entity.ToolItemEntity;


@Repository
public interface ToolItemJpaRepository extends JpaRepository<ToolItemEntity, UUID>{

    List<ToolItemEntity> findAllByToolCatalogEntityId(UUID toolCatalogEntityId);
    void deleteAllByToolCatalogEntityId(UUID toolCatalogEntityId);

    @Query("SELECT ti FROM ToolItemEntity ti WHERE ti.toolCatalogEntity.id = :toolCatalogId AND ti.status = :status")
    List<ToolItemEntity> findAllByToolCatalogEntityIdByToolItemStatus(
        @Param("toolCatalogId") UUID toolCatalogId, 
        @Param("status") ToolItemStatus status
    );
}   
