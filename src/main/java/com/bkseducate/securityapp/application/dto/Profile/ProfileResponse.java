package com.bkseducate.securityapp.application.dto.Profile;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Interfaz base para respuestas de perfil (Usuario o Proveedor)", subTypes = { UserResponse.class,
        SupplierResponse.class })
public sealed interface ProfileResponse permits SupplierResponse, UserResponse {

}
