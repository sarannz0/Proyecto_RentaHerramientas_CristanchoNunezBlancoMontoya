package com.bkseducate.securityapp.application.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para información de país")
public class CountryDTO {
    @Schema(description = "ID del país", example = "c2eebc99-9c0b-4ef8-bb6d-6bb9bd380a33")
    private UUID id;

    @Schema(description = "Nombre del país", example = "Colombia", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50)
    private String name;

    @Schema(description = "Código ISO del país", example = "CO", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "El isocode es obligatorio")
    @Size(max = 15)
    private String isocode;

    public CountryDTO() {
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

    public String getIsocode() {
        return isocode;
    }

    public void setIsocode(String isocode) {
        this.isocode = isocode;
    }

}
