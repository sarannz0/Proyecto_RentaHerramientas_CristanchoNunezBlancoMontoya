package com.bkseducate.securityapp.application.dto;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para información de dirección")
public class AddressDTO {
    @Schema(description = "ID de la dirección", example = "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
    private UUID id;

    @Schema(description = "Dirección física", example = "Calle 123 # 45-67", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 55)
    private String address;

    @Schema(description = "Ciudad asociada", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "La ciudad es obligatoria")
    private CityDTO city;

    @Schema(description = "Código postal", example = "110111", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Size(max = 15)
    @Column(name = "postal_code", length = 15)
    private String postal_code;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CityDTO getCity() {
        return city;
    }

    public void setCity(CityDTO city) {
        this.city = city;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

}