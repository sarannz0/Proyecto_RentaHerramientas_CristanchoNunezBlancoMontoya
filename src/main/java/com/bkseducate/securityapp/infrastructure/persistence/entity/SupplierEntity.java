package com.bkseducate.securityapp.infrastructure.persistence.entity;

import java.util.UUID;

import com.bkseducate.securityapp.domain.model.Address;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "suppliers")
public class SupplierEntity {
    @Id
    @Column(name = "user_id")
    UUID userId;

    @Column(name = "company_name")
    String companyName;

    @Column(name = "address_id")
    AddressEntity address;

    public SupplierEntity() {}

   public SupplierEntity(
        String companyName,
        AddressEntity address
    ) {
        this.userId = UUID.randomUUID();
        this.companyName = companyName;
        this.address = address;
    }

   public UUID getUserId() {
    return userId;
   }

   public void setUserId(UUID userId) {
    this.userId = userId;
   }

   public String getCompanyName() {
    return companyName;
   }

   public void setCompanyName(String companyName) {
    this.companyName = companyName;
   }

   public AddressEntity getAddress() {
    return address;
   }

   public void setAddres(AddressEntity address) {
    this.address = address;
   }
    
    
}
