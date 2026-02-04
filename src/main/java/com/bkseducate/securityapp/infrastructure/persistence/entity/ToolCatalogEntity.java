package com.bkseducate.securityapp.infrastructure.persistence.entity;

import java.util.UUID;

import com.bkseducate.securityapp.domain.model.ToolItemStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    private double price;

    @Column(nullable = false)
    private ToolItemStatus status;

    @Column(nullable = false)
    private String desciption;

    @Column(nullable = false, name = "image_url")
    private String imageUrl;

    public ToolCatalogEntity(SupplierEntity supplier, String name, double price, ToolItemStatus status,
            String desciption, String imageUrl) {
        this.supplier = supplier;
        this.name = name;
        this.price = price;
        this.status = status;
        this.desciption = desciption;
        this.imageUrl = imageUrl;
    }

    public ToolCatalogEntity() {
    }


    //@ManyToMany(mappedBy = "tools")
    //private List<Rent> rents;
}
