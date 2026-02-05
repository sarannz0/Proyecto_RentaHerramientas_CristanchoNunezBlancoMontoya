package com.bkseducate.securityapp.application.dto.ToolCatalog;

import java.math.BigDecimal;
import java.util.UUID;

import com.bkseducate.securityapp.domain.model.ToolCatalogStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ToolCatalogRequest(
    @NotNull(message = "El supplierId es requerido")
    UUID supplierId,
    @NotBlank(message = "El name es requerido")
    String name,
    @NotNull(message = "El price es requerido")
    BigDecimal price,
    @NotNull(message = "El status es requerido")
    ToolCatalogStatus status,
    @NotBlank(message = "El description es requerido")
    String description
) {}
