package com.bkseducate.securityapp.application.usecase.ToolCatalog;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bkseducate.securityapp.domain.model.ToolCatalog;
import com.bkseducate.securityapp.domain.ports.ToolCatalogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListToolCatalogUseCase {
    
    private final ToolCatalogRepository toolCatalogRepository;

    public List<ToolCatalog> execute() {
        return toolCatalogRepository.findAll();
    }
}
