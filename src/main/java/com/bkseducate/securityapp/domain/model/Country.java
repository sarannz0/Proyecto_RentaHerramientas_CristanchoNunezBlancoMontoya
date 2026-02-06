package com.bkseducate.securityapp.domain.model;

import java.util.UUID;

public class Country {
    private UUID id;
    private String name;
    private String isocode;

    private Country() {
    }

    public static Country create(
        String name, 
        String isocode
    ) {
        Country country = new Country();
        country.id = UUID.randomUUID();
        country.name = name;
        country.isocode = isocode;

        return country;
    }

    public static Country recreate(
        UUID id,
        String name, 
        String isocode
    ) {
        Country country = new Country();
        country.id = id;
        country.name = name;
        country.isocode = isocode;

        return country;
    }

    public UUID getId() {
        return id;
    }
 

    public String getName() {
        return name;
    }


    public String getIsocode() {
        return isocode;
    }

}
