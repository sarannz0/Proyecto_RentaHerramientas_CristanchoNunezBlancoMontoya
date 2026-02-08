package com.bkseducate.securityapp.application.usecase.ToolCatalog;

import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bkseducate.securityapp.application.dto.ToolCatalog.ToolCatalogUpdateRequest;
import com.bkseducate.securityapp.domain.model.ToolCatalog;
import com.bkseducate.securityapp.domain.ports.FileStorageRepository;
import com.bkseducate.securityapp.domain.ports.ToolCatalogRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateToolCatalogUseCase {
    
    private final ToolCatalogRepository toolCatalogRepository;   
    private final FileStorageRepository fileStorageRepository; 

    @Transactional
    public void execute(UUID catalogId, UUID supplierId, ToolCatalogUpdateRequest request, MultipartFile img) {
        ToolCatalog catalog = toolCatalogRepository.findById(catalogId)
            .orElseThrow(() -> new EntityNotFoundException("No se pudo encontrar el Catalogo con ID " + catalogId));

        if (!catalog.getSupplier().getUserId().equals(supplierId))
            throw new RuntimeException("No tienes permiso para acceder a este recurso");

        String logicUrl = catalog.getImageUrl();
        if (img != null && !img.isEmpty()) {
            try {
                String extension = img.getContentType().split("/")[1]; 
                String fileName = UUID.randomUUID().toString() + "." + extension;
                
                byte[] contenido = img.getBytes();
                String savedName = fileStorageRepository.save(contenido, fileName);
                logicUrl = "/imagenes/" + savedName;
            } catch (IOException e) {
                throw new RuntimeException("Error al procesar la imagen: " + e.getMessage());
            }
        }

        toolCatalogRepository.save(
            ToolCatalog.reconstruct(
                catalog.getId(),
                catalog.getSupplier(),
                request.name(),
                request.price(),
                catalog.getStatus(),
                request.description(),
                logicUrl
            )
        );
    }
}
