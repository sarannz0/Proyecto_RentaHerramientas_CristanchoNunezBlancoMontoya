package com.bkseducate.securityapp.application.usecase.ToolItem;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bkseducate.securityapp.domain.model.ToolCatalog;
import com.bkseducate.securityapp.domain.model.ToolItem;
import com.bkseducate.securityapp.domain.model.ToolItemStatus;
import com.bkseducate.securityapp.domain.ports.ToolCatalogRepository;
import com.bkseducate.securityapp.domain.ports.ToolItemRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ToolItemCreateUseCase {
    private final ToolItemRepository itemRepository;
    private final ToolCatalogRepository toolCatalogRepository;
    public void execute(UUID id, UUID supplierId) {
        ToolCatalog toolCatalogEntity = toolCatalogRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("No se pudo encontrar el Catalogo con ID" + id));

        if (!toolCatalogEntity.getSupplier().getUserId().equals(supplierId)) 
            throw new RuntimeException("No tienes permisos para Gestionar este producto");

        ToolItem toolItem = ToolItem.create(
            toolCatalogEntity,
            ToolItemStatus.AVAILABLE,
            true
        ); 

        itemRepository.save(toolItem);
    }
}
