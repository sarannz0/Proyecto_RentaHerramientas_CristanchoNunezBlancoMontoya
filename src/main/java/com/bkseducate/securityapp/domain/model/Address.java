package com.bkseducate.securityapp.domain.model;

import java.util.UUID;

public class Address {

    private UUID id;
    private String address;
    private String postalCode;
    private City city;

    public Address() {
    }

    public static Address create(
            String address,
            String postalCode,
            City city
    ) {
        Address addre = new Address();
        addre.address = address;
        addre.postalCode = postalCode;
        addre.city = city;
        return addre;
    }

    public static Address recreate(
        UUID id,
        String address,
        String postalCode,
        City city
) {
    Address addre = new Address();
    addre.id = id;
    addre.address = address;
    addre.postalCode = postalCode;
    addre.city = city;
    return addre;
}

    public UUID getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public City getCity() {
        return city;
    }
}
