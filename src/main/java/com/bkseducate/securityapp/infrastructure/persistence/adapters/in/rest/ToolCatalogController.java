package com.bkseducate.securityapp.infrastructure.persistence.adapters.in.rest;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bkseducate.securityapp.application.dto.ToolCatalog.ToolCatalogRequest;
import com.bkseducate.securityapp.application.usecase.ToolCatalog.CreateToolCatalogUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/tools")
public class ToolCatalogController {
    private final CreateToolCatalogUseCase createToolCatalogUseCase;
    private final ObjectMapper objectMapper;

    public ToolCatalogController(
        ObjectMapper objectMapper,
        CreateToolCatalogUseCase createToolCatalogUseCase
    ) {
        this.createToolCatalogUseCase = createToolCatalogUseCase;
        this.objectMapper = objectMapper;
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void createToolCatalog(
        @RequestPart("imgFile") MultipartFile imgFile,
        @RequestPart String request
    ) throws JsonProcessingException {
        createToolCatalogUseCase.execute(
            objectMapper.readValue(request, ToolCatalogRequest.class), 
            imgFile
        );
    }
}
