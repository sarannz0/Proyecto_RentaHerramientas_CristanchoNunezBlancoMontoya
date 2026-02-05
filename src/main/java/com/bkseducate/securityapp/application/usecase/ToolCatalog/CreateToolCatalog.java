package com.bkseducate.securityapp.application.usecase.ToolCatalog;

import org.springframework.stereotype.Service;

import com.bkseducate.securityapp.domain.ports.FileStorageRepository;
import com.bkseducate.securityapp.domain.ports.ToolCatalogRepository;

@Service
public class CreateToolCatalog {
   
    private final ToolCatalogRepository catalogRepository;
    private final FileStorageRepository fileStorageRepository;

}
