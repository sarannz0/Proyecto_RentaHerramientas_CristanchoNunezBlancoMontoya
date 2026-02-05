package com.bkseducate.securityapp.domain.ports;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.bkseducate.securityapp.domain.model.ToolItem;

public interface ToolItemRepository {
    List<ToolItem> findAll();
    Optional<ToolItem> findById(UUID id);
    ToolItem save(ToolItem tool);
    ToolItem update(UUID id, ToolItem tool);
    void delete(UUID id); 
}
