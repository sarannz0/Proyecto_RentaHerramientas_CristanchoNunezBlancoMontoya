package com.bkseducate.securityapp.application.dto.ToolCatalog;

import java.math.BigDecimal;

import com.bkseducate.securityapp.domain.model.ToolCatalogStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Solicitud para crear un catálogo de herramientas")
public record ToolCatalogRequest(
        @Schema(description = "Nombre de la herramienta", example = "Taladro Percutor", requiredMode = Schema.RequiredMode.REQUIRED) @NotBlank(message = "El name es requerido") String name,

        @Schema(description = "Precio de alquiler por día", example = "50000.00", requiredMode = Schema.RequiredMode.REQUIRED) @NotNull(message = "El price es requerido") BigDecimal price,

        @Schema(description = "Estado inicial del catálogo", example = "AVAIBLE", requiredMode = Schema.RequiredMode.REQUIRED) @NotNull(message = "El status es requerido") ToolCatalogStatus status,

        @Schema(description = "Descripción detallada de la herramienta", example = "Taladro percutor de 1/2 pulgada, 800W", requiredMode = Schema.RequiredMode.REQUIRED) @NotBlank(message = "El description es requerido") String description) {
}
