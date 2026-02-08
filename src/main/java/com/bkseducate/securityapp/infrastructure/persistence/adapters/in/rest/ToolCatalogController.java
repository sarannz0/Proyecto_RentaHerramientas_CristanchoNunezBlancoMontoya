package com.bkseducate.securityapp.infrastructure.persistence.adapters.in.rest;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bkseducate.securityapp.application.dto.ToolCatalog.ToolCatalogRequest;
import com.bkseducate.securityapp.application.dto.ToolCatalog.ToolCatalogUpdateRequest;
import com.bkseducate.securityapp.application.usecase.ToolCatalog.CreateToolCatalogUseCase;
import com.bkseducate.securityapp.application.usecase.ToolCatalog.DeleteToolCatalogUseCase;
import com.bkseducate.securityapp.application.usecase.ToolCatalog.UpdateToolCatalogUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tools")
@RequiredArgsConstructor
public class ToolCatalogController {
    private final CreateToolCatalogUseCase createToolCatalogUseCase;
    private final ObjectMapper objectMapper;
    private final DeleteToolCatalogUseCase deleteToolCatalogUseCase;
    private final UpdateToolCatalogUseCase updateToolCatalogUseCase;

    @PreAuthorize("hasRole('SUPPLIER')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<HttpStatus> createToolCatalog(
        @RequestPart("imgFile") MultipartFile imgFile,
        @RequestPart String request,
        Authentication authentication
    ) throws JsonProcessingException {
        UUID supplierId = (UUID) authentication.getPrincipal();
        createToolCatalogUseCase.execute(
            supplierId,
            objectMapper.readValue(request, ToolCatalogRequest.class), 
            imgFile
        );
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @DeleteMapping("/delete/{catalogId}")
    public ResponseEntity<HttpStatus> deleteToolCatalog(
        @PathVariable UUID catalogId,
        Authentication authentication
    ) throws JsonProcessingException {
        UUID supplierId = (UUID) authentication.getPrincipal();
        deleteToolCatalogUseCase.execute(
            catalogId,
            supplierId
        );
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, path = "/update/{catalogId}")
    public ResponseEntity<HttpStatus> updateToolCatalog(
        @RequestPart("imgFile") MultipartFile imgFile,
        @PathVariable UUID catalogId,
        @RequestPart String request,
        Authentication authentication
    ) throws JsonProcessingException {
        UUID supplierId = (UUID) authentication.getPrincipal();
        updateToolCatalogUseCase.execute(
            catalogId,
            supplierId,
            objectMapper.readValue(request, ToolCatalogUpdateRequest.class), 
            imgFile
        );
        return ResponseEntity.ok().build();
    }
}
