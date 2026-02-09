package com.bkseducate.securityapp.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Estado del catálogo de herramienta", example = "AVAIBLE")
public enum ToolCatalogStatus {
    @Schema(description = "Disponible")
    AVAIBLE,
    @Schema(description = "No disponible")
    UNAVAIABLE
}
