package com.bkseducate.securityapp.domain.ports;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.bkseducate.securityapp.domain.model.ToolItem;
import com.bkseducate.securityapp.domain.model.ToolItemStatus;
import com.bkseducate.securityapp.infrastructure.persistence.entity.ToolItemEntity;

public interface ToolItemRepository {
    List<ToolItem> findAll();
    List<ToolItem> findAllByToolCatalogId(UUID toolCatalogId);
    List<ToolItem> findAllByToolCatalogEntityIdByToolItemStatus(UUID toolCatalogId, ToolItemStatus status);
    Optional<ToolItem> findById(UUID id);
    ToolItem save(ToolItem tool);
    ToolItem update(UUID id, ToolItem tool);
    void delete(UUID id); 
    void deleteAllByToolCatalogEntityId(UUID toolCatalogEntityId);
}
