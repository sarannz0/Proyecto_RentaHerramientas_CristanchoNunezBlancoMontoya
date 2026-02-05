package com.bkseducate.securityapp.infrastructure.persistence.entity;

import java.math.BigDecimal;
import java.util.UUID;

import com.bkseducate.securityapp.domain.model.ToolCatalogStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "tools")
public class ToolCatalogEntity {
    @Id
    @Column(columnDefinition = "CHAR(36)", length = 36)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "supplier_fk",
        referencedColumnName = "userId",
        nullable = false,
        updatable = false
    )
    private SupplierEntity supplier;

    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ToolCatalogStatus status;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, name = "image_url")
    private String imageUrl;

    public ToolCatalogEntity(SupplierEntity supplier, String name, BigDecimal price, ToolCatalogStatus status,
            String description, String imageUrl) {
        this.supplier = supplier;
        this.name = name;
        this.price = price;
        this.status = status;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public ToolCatalogEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public SupplierEntity getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierEntity supplier) {
        this.supplier = supplier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ToolCatalogStatus getStatus() {
        return status;
    }

    public void setStatus(ToolCatalogStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    //@ManyToMany(mappedBy = "tools")
    //private List<Rent> rents;
}
