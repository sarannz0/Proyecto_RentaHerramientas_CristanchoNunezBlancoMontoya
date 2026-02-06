package com.bkseducate.securityapp.domain.model;

import java.util.UUID;

public class SupplierM {
    UUID userId;
    String companyName;
    Address address;

    public SupplierM() {}

     public static SupplierM create(
        UUID userId,
        String companyName,
        Address address
    ) {
        SupplierM supplier = new SupplierM();
        supplier.userId = userId;
        supplier.companyName = companyName;
        supplier.address = address;
        return supplier;
    }   

    public static SupplierM reconstruct(
        UUID userId,
        String companyName,
        Address address
    ) {
        SupplierM supplier = new SupplierM();
        supplier.userId = userId;
        supplier.companyName = companyName;
        supplier.address = address;
        return supplier;
    } 
    
    public void updateInfo(
        String companyName,
        Address address
    ) {
        this.companyName = companyName;
        this.address = address;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Address getAddress() {
        return address;
    }
}
