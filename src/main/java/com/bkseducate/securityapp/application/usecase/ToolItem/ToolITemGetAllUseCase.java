package com.bkseducate.securityapp.application.usecase.ToolItem;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bkseducate.securityapp.domain.model.ToolCatalog;
import com.bkseducate.securityapp.domain.model.ToolItem;
import com.bkseducate.securityapp.domain.ports.ToolCatalogRepository;
import com.bkseducate.securityapp.domain.ports.ToolItemRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ToolITemGetAllUseCase {
    
    private final ToolItemRepository toolItemRepository;
    private final ToolCatalogRepository toolCatalogRepository;

    public List<ToolItem> execute(UUID catalogId, UUID supplierId) {
        ToolCatalog toolCatalogEntity = toolCatalogRepository.findById(catalogId)
            .orElseThrow(() -> new EntityNotFoundException("No se pudo encontrar el Catalogo con ID" + catalogId));

        if (!toolCatalogEntity.getSupplier().getUserId().equals(supplierId)) 
            throw new RuntimeException("No tienes permisos para Gestionar este producto");
    
        return toolItemRepository.findAllByToolCatalogId(catalogId);
    }
}
