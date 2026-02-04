package com.bkseducate.securityapp.application.dto.User;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserUpdateResponse(
    @Schema(description = "El Nombre de usuario", example = "usuario123", requiredMode = Schema.RequiredMode.REQUIRED)
    String name,

    @Schema(description = "Email de usuario", example = "usuario@email.com", requiredMode = Schema.RequiredMode.REQUIRED)
    String email
) {
    
}
