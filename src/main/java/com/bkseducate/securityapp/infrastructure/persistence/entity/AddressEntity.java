package com.bkseducate.securityapp.infrastructure.persistence.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="addresses")
public class AddressEntity {
    @Id
    @Column(columnDefinition = "CHAR(36)", length = 36)
    private UUID id;


    @Size(max = 55)
    @Column(name = "address", length = 55, nullable = false)
    private String address;

    @NotNull(message = "La dirección es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    private CityEntity city;
    

    @Size(max = 15)
    @Column(name = "postalCode", length = 15, nullable = false)
    private String postalCode;

   

    public AddressEntity(UUID id, @Size(max = 55) String address,
            @NotNull(message = "La dirección es obligatoria") CityEntity city, @Size(max = 15) String postalCode) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
    }

    public AddressEntity() {
    }

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

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

    public String getpostalCode() {
        return postalCode;
    }

    public void setpostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
