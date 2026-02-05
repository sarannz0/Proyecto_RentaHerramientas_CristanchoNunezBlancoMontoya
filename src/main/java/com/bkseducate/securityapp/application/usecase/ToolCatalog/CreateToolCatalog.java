package com.bkseducate.securityapp.application.usecase.ToolCatalog;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.bkseducate.securityapp.application.dto.ToolCatalog.ToolCatalogRequest;
import com.bkseducate.securityapp.domain.ports.FileStorageRepository;
import com.bkseducate.securityapp.domain.ports.ToolCatalogRepository;

@Service
public class CreateToolCatalog {
   
    private final ToolCatalogRepository catalogRepository;
    private final FileStorageRepository fileStorageRepository;

    public CreateToolCatalog(
        ToolCatalogRepository catalogRepository,
        FileStorageRepository fileStorageRepository
    ) {
        this.catalogRepository = catalogRepository;
        this.fileStorageRepository = fileStorageRepository;
    }

    public void execute(ToolCatalogRequest request) {
        String logicUrl;
        try {
            byte[] contenido = request.img().getBytes();
            String originName = request.name();
            String savedName = fileStorageRepository.save(contenido, originName);
            logicUrl = "/imagenes/" + savedName;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }


        
    }
}
