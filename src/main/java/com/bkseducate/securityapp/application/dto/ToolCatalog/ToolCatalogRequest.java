package com.bkseducate.securityapp.application.dto.ToolCatalog;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.bkseducate.securityapp.domain.model.ToolCatalogStatus;

import jakarta.validation.constraints.NotBlank;

public record ToolCatalogRequest(
    @NotBlank(message = "El supplierId es requerido")
    UUID supplierId,
    @NotBlank(message = "El name es requerido")
    String name,
    @NotBlank(message = "El price es requerido")
    BigDecimal price,
    @NotBlank(message = "El status es requerido")
    ToolCatalogStatus status,
    @NotBlank(message = "El description es requerido")
    String description,
    @NotBlank(message = "La img es requerida")
    MultipartFile img
) {}
