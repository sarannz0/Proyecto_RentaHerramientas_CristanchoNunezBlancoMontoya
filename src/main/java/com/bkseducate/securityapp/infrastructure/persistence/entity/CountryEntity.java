package com.bkseducate.securityapp.infrastructure.persistence.entity;


import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="countries")
public class CountryEntity {
   
    @Id
    @Column(columnDefinition = "CHAR(36)", length = 36)
    private UUID id;

    @NotBlank
    @Size(max = 50)
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @NotBlank
    @Size(max = 15)
    @Column(name = "isocode", length = 15, nullable = false)
    private String isocode;

    public CountryEntity(UUID id, @NotBlank @Size(max = 50) String name, @NotBlank @Size(max = 15) String isocode) {
        this.id = id;
        this.name = name;
        this.isocode = isocode;
    }

    public CountryEntity() {
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
