package com.bkseducate.securityapp.domain.ports;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.bkseducate.securityapp.domain.model.ToolCatalog;

public interface ToolCatalogRepository {
    
    List<ToolCatalog> findAll();
    Optional<ToolCatalog> findById(UUID id);
    ToolCatalog save(ToolCatalog tool);
    ToolCatalog update(UUID id, ToolCatalog tool);
    void delete(UUID id);
}
