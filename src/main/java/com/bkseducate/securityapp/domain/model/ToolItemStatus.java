package com.bkseducate.securityapp.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Estado físico del ítem de herramienta", example = "AVAILABLE")
public enum ToolItemStatus {
    @Schema(description = "Disponible para alquiler")
    AVAILABLE,
    @Schema(description = "No disponible")
    UNAVAILABLE,
    @Schema(description = "Dañado")
    DAMAGED,
    @Schema(description = "En mantenimiento")
    MAINTENANCE
}
