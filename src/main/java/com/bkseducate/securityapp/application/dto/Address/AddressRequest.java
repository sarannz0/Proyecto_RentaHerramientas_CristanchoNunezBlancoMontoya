package com.bkseducate.securityapp.application.dto.Address;

import com.bkseducate.securityapp.domain.model.City;

public record AddressRequest(
    String address,
    String postalCode,
    City city
) {}
