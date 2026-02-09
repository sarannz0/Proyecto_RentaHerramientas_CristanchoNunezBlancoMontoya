package com.bkseducate.securityapp.application.dto.User;

import java.util.UUID;

import com.bkseducate.securityapp.domain.model.UserStatus;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta tras actualizar el estado de un usuario")
public record StatusUserResponse(
        @Schema(description = "ID del usuario", example = "550e8400-e29b-41d4-a716-446655440000") UUID userId,

        @Schema(description = "Nuevo estado del usuario", example = "BLOCKED") UserStatus status) {
}
