package com.bkseducate.securityapp.infrastructure.persistence.entity;

import java.util.UUID;

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
    String addresId;

    public SupplierEntity() {}

   public SupplierEntity(
        UUID userId,
        String companyName,
        String addresId
    ) {
        this.userId = userId;
        this.companyName = companyName;
        this.addresId = addresId;
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

   public String getAddresId() {
    return addresId;
   }

   public void setAddresId(String addresId) {
    this.addresId = addresId;
   }
    
    
}
