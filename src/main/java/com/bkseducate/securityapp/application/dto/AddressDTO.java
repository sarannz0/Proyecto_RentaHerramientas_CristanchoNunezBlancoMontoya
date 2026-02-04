package com.bkseducate.securityapp.application.dto;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AddressDTO {
    private UUID id;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 55)
    private String address;

    @NotNull(message = "La ciudad es obligatoria")
    private CityDTO city;

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