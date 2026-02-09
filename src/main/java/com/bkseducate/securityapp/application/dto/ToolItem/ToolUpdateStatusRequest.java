package com.bkseducate.securityapp.application.dto.ToolItem;

import com.bkseducate.securityapp.domain.model.ToolItemStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Solicitud para actualizar el estado físico de un ítem")
public record ToolUpdateStatusRequest(
        @Schema(description = "Nuevo estado del ítem", example = "DAMAGED", requiredMode = Schema.RequiredMode.REQUIRED) @NotNull(message = "El status es requerido") ToolItemStatus status) {
}
