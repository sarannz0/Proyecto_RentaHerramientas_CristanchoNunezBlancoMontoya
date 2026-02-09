package com.bkseducate.securityapp.application.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para información de ciudad")
public class CityDTO {
    @Schema(description = "ID de la ciudad", example = "b1eebc99-9c0b-4ef8-bb6d-6bb9bd380a22")
    private UUID id;

    @Schema(description = "Nombre de la ciudad", example = "Bogotá", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "El nombre de la ciudad es obligatorio")
    private String name;

    @Schema(description = "País asociado", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "El país es obligatorio")
    private CountryDTO country;

    public CityDTO() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountryDTO getCountry() {
        return country;
    }

    public void setCountry(CountryDTO country) {
        this.country = country;
    }

}
