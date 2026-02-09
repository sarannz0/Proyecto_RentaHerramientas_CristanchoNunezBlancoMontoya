package com.bkseducate.securityapp.application.dto.Profile.updateProfile;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Interfaz base para actualizaciones de perfil", subTypes = { SupplierUpdateRequest.class,
        UserUpdateRequest.class, SupplierUpdateResponse.class, UserUpdateResponse.class })
public sealed interface ProfileUpdate
        permits SupplierUpdateRequest, UserUpdateRequest, SupplierUpdateResponse, UserUpdateResponse {

}
