package com.bkseducate.securityapp.application.dto.ToolCatalog;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ToolCatalogUpdateRequest(
    @NotBlank(message = "El name es requerido")
    String name,

    @NotNull(message = "El price es requerido")
    BigDecimal price,

    @NotBlank(message = "El description es requerido")
    String description
) {}
