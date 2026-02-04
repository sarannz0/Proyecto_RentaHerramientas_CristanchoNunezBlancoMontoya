package com.bkseducate.securityapp.domain.model;

import java.util.UUID;

public class City {
    private UUID id;
    private String name;
    private Country country;

    public City() {
    }

    public static City create(
        String name, 
        Country country
    ) {
        City city = new City();
        city.name = name;
        city.country = country;

        return city;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public Country getCountry() {
        return country;
    }

}
