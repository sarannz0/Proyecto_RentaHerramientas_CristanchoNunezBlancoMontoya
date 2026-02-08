package com.bkseducate.securityapp.application.usecase.ToolCatalog;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bkseducate.securityapp.domain.model.ToolCatalog;
import com.bkseducate.securityapp.domain.ports.ToolCatalogRepository;
import com.bkseducate.securityapp.domain.ports.ToolItemRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteToolCatalogUseCase {
    
    private final ToolCatalogRepository toolCatalogRepository;
    private final ToolItemRepository toolItemRepository;

    @Transactional
    public void execute(UUID catalogId, UUID supplierId) {
        ToolCatalog catalog = toolCatalogRepository.findById(catalogId)
            .orElseThrow(() -> new EntityNotFoundException("No se pudo encontrar el Catalogo con ID " + catalogId));

        if (!catalog.getSupplier().getUserId().equals(supplierId))
            throw new RuntimeException("No tienes permiso para acceder a este recurso");

        toolItemRepository.deleteAllByToolCatalogEntityId(catalog.getId());
        toolCatalogRepository.delete(catalogId);
    }
}
