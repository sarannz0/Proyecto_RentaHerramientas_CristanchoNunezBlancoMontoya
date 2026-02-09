package com.bkseducate.securityapp.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Estado del usuario en el sistema", example = "ACTIVE")
public enum UserStatus {
    @Schema(description = "Usuario activo")
    ACTIVE,
    @Schema(description = "Usuario bloqueado")
    BLOCKED,
    @Schema(description = "Usuario inactivo")
    INACTIVE
}
