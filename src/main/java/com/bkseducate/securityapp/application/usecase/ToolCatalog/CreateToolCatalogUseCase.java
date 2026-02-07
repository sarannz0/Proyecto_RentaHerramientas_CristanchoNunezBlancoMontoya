package com.bkseducate.securityapp.application.usecase.ToolCatalog;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bkseducate.securityapp.application.dto.ToolCatalog.ToolCatalogRequest;
import com.bkseducate.securityapp.domain.exceptions.UserNotFoundException;
import com.bkseducate.securityapp.domain.model.ToolCatalog;
import com.bkseducate.securityapp.domain.ports.FileStorageRepository;
import com.bkseducate.securityapp.domain.ports.SupplierRepository;
import com.bkseducate.securityapp.domain.ports.ToolCatalogRepository;
import com.bkseducate.securityapp.domain.ports.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class CreateToolCatalogUseCase {
   
    private final ToolCatalogRepository catalogRepository;
    private final FileStorageRepository fileStorageRepository;
    private final SupplierRepository supplierRepository;
    private final UserRepository userRepository;

    public CreateToolCatalogUseCase(
        SupplierRepository supplierRepository,
        ToolCatalogRepository catalogRepository,
        FileStorageRepository fileStorageRepository,
        UserRepository userRepository
    ) {
        this.supplierRepository = supplierRepository;
        this.catalogRepository = catalogRepository;
        this.fileStorageRepository = fileStorageRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void execute(UUID userId, ToolCatalogRequest request, MultipartFile imgFile) {
        String logicUrl;
        try {
            byte[] contenido = imgFile.getBytes();
            String originName = request.name();
            String savedName = fileStorageRepository.save(contenido, originName);
            logicUrl = "/imagenes/" + savedName;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        ToolCatalog saveTool =  ToolCatalog.create(
            supplierRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("No se pudo encontrar el proveedor con ID " + userId)),
            request.name(),
            request.price(),
            request.status(),
            request.description(),
            logicUrl
        );

        catalogRepository.save(saveTool);
        
    }
}
