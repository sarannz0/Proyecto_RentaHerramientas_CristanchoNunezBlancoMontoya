package com.bkseducate.securityapp.domain.model;

import java.util.UUID;

public class SupplierM {
    UUID userId;
    String companyName;
    String addressId;

    public SupplierM() {}

     public static SupplierM create(
        UUID userId,
        String companyName,
        String addressId
    ) {
        SupplierM supplier = new SupplierM();
        supplier.userId = userId;
        supplier.companyName = companyName;
        supplier.addressId = addressId;
        return supplier;
    }   

    public static SupplierM reconstruct(
        UUID userId,
        String companyName,
        String addressId
    ) {
        SupplierM supplier = new SupplierM();
        supplier.userId = userId;
        supplier.companyName = companyName;
        supplier.addressId = addressId;
        return supplier;
    }   

    public UUID getUserId() {
        return userId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getAddressId() {
        return addressId;
    }
}
