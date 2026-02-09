package com.bkseducate.securityapp.application.dto.User;

import java.util.UUID;

import com.bkseducate.securityapp.domain.model.UserStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Solicitud de cambio de estado de usuario (activar/bloquear)")
public record StatusUserRequest(
    @Schema(description = "ID del usuario", example = "550e8400-e29b-41d4-a716-446655440000", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "El ID del usuario es requerido")
    UUID userId,
    @Schema(description = "Estado del usuario (ACTIVE, BLOCKED, INACTIVE)", example = "BLOCKED", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "El Status del usuario es requerido")
    UserStatus status
) {}
