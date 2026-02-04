package com.bkseducate.securityapp.application.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CityDTO {
    private UUID id;

    @NotBlank(message = "El nombre de la ciudad es obligatorio")
    private String name;

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
