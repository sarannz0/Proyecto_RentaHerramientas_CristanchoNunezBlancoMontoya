package com.bkseducate.securityapp.application.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CountryDTO {
    private UUID id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50)
    private String name;

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
