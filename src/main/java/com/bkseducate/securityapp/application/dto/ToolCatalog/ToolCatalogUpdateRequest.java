package com.bkseducate.securityapp.application.dto.ToolCatalog;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Solicitud para actualizar un catálogo de herramientas")
public record ToolCatalogUpdateRequest(
        @Schema(description = "Nombre de la herramienta", example = "Taladro Percutor Profesional", requiredMode = Schema.RequiredMode.REQUIRED) @NotBlank(message = "El name es requerido") String name,

        @Schema(description = "Precio de alquiler por día", example = "55000.00", requiredMode = Schema.RequiredMode.REQUIRED) @NotNull(message = "El price es requerido") BigDecimal price,

        @Schema(description = "Descripción detallada de la herramienta", example = "Taladro percutor industrial de 1/2 pulgada", requiredMode = Schema.RequiredMode.REQUIRED) @NotBlank(message = "El description es requerido") String description) {
}
