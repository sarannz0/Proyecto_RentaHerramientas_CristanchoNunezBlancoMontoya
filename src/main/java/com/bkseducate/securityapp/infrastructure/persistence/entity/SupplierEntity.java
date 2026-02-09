package com.bkseducate.securityapp.infrastructure.persistence.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "suppliers")
public class SupplierEntity {
    @Id
    @Column(name = "user_id", columnDefinition = "CHAR(36)", length = 36)
    UUID userId;

    @Column(name = "company_name")
    String companyName;

    @ManyToOne
    @JoinColumn(name = "address_fk", nullable = false, referencedColumnName = "id")
    AddressEntity address;

    public SupplierEntity() {}

   public SupplierEntity(
        UUID id,
        String companyName,
        AddressEntity address
    ) {
        this.userId = id;
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
