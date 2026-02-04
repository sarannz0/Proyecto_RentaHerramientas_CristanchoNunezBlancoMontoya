package com.bkseducate.securityapp.application.dto.Profile.updateProfile;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserUpdateRequest(
    @Schema(description = "El Nombre de usuario", example = "usuario123", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "El nombre es requerido")
    String name,

    @Schema(description = "Email de usuario", example = "usuario@email.com", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "El email es requerido")
    @Email(message = "Email debe tener un formato válido")
    String email
)  implements ProfileUpdate {
    
}
