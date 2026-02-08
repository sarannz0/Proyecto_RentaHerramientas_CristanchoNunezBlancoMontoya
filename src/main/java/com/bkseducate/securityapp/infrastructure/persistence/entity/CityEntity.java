package com.bkseducate.securityapp.infrastructure.persistence.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="cities")
public class CityEntity {

    @Id
    @Column(columnDefinition = "CHAR(36)", length = 36)
    private UUID id;

    @NotBlank
    @Size(max = 50)
    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false) 
    @JoinColumn(name = "country_id", nullable = false) 
    private CountryEntity country;

   
    public CityEntity(UUID id, @NotBlank @Size(max = 50) String name, CountryEntity country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public CityEntity() {
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

    public CountryEntity getCountry() {
        return country;
    }

    public void setCountry(CountryEntity country) {
        this.country = country;
    }
}
