package com.bkseducate.securityapp.infrastructure.persistence.entity;

import java.util.List;
import java.util.UUID;

import com.bkseducate.securityapp.domain.model.ToolStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tools")
public class ToolEntity {
    @Id
    @Column(columnDefinition = "CHAR(36)", length = 36)
    private UUID id;

    @Column(nullable = false)
    @NotNull(message = "El nombre es obligatorio")
    private String name;

    @Size(min=0, message = "El stock no puede ser negativo")
    @NotNull(message = "El stock es obligatorio")
    @Column(nullable = false)
    private Integer stock;
    
    @Column(nullable = false)
    @Size(min=0)
    @NotNull(message = "El precio es obligatorio")
    private double price;

    @Column(nullable = false)
    @NotNull(message = "El status es obligatorio")
    private ToolStatus status;

    @Column(nullable = false)
    @NotNull(message = "La descripción es obligatoria")
    private String desciption;

    @Column(nullable = false, name = "image_url")
    @NotNull(message = "La URL de la imagen es obligatoria")
    private String imageUrl;

    public ToolEntity() {
    }

    public ToolEntity(UUID id, @NotNull(message = "El nombre es obligatorio") String name,
            @Size(min = 0, message = "El stock no puede ser negativo") @NotNull(message = "El stock es obligatorio") Integer stock,
            @Size(min = 0) @NotNull(message = "El precio es obligatorio") double price,
            @NotNull(message = "El status es obligatorio") ToolStatus status,
            @NotNull(message = "La descripción es obligatoria") String desciption,
            @NotNull(message = "La URL de la imagen es obligatoria") String imageUrl) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.status = status;
        this.desciption = desciption;
        this.imageUrl = imageUrl;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ToolStatus getStatus() {
        return status;
    }

    public void setStatus(ToolStatus status) {
        this.status = status;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImage_url(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    //@ManyToMany(mappedBy = "tools")
    //private List<Rent> rents;
}
