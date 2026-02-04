package com.bkseducate.securityapp.application.dto.User;

import java.util.UUID;

import com.bkseducate.securityapp.domain.model.UserStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Solicitud de cambio de Status")
public record StatusUserRequest(
    @Schema(description = "ID del usuario", example = "12KJGGYG-JGHHGYG-12321KHG3B", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "El ID del usuario es requerido")
    UUID userId,
    @Schema(description = "Status del usuario", example = "USER", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "El Status del usuario es requerido")
    UserStatus status
) {}
