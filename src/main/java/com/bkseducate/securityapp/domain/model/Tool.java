package com.bkseducate.securityapp.domain.model;

import java.util.UUID;

import com.bkseducate.securityapp.domain.exceptions.DomainException;

public class Tool {
    private UUID id;
    private String name;
    private Integer stock;
    private double price;
    private ToolStatus status;
    private String desciption;
    private String imageUrl;

    private Tool() {}

    public static Tool create(
        String name,
        Integer stock,
        double price,
        ToolStatus status,
        String desciption,
        String imageUrl
    ) {
        if (stock < 0) throw new DomainException("El stock no puede ser negativo");
        if (price < 0) throw new DomainException("El precio no puede ser negativo");
        if (name == null || name.isBlank()) throw new DomainException("El nombre no puede estar vacío");
        if (desciption == null || desciption.isBlank()) throw new DomainException("La descripción no puede estar vacía");
        if (imageUrl == null || imageUrl.isBlank()) throw new DomainException("La URL de la imagen no puede estar vacía");
        
        Tool tool = new Tool();
        tool.id = UUID.randomUUID();
        tool.name = name;
        tool.stock = stock;
        tool.price = price;
        tool.status = status;
        tool.desciption = desciption;
        tool.imageUrl = imageUrl;
        return tool;
    }

    public static Tool reconstruct(
        UUID id,
        String name,
        Integer stock,
        double price,
        ToolStatus status,
        String desciption,
        String imageUrl
    ) { 
        Tool tool = new Tool();
        tool.id = id;
        tool.name = name;
        tool.stock = stock;
        tool.price = price;
        tool.status = status;
        tool.desciption = desciption;
        tool.imageUrl = imageUrl;
        return tool;
    }

    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Integer getStock() {
        return stock;
    }
    public double getPrice() {
        return price;
    }
    public ToolStatus getStatus() {
        return status;
    }
    public String getDesciption() {
        return desciption;
    }
    public String getImageUrl() {
        return imageUrl;
    }

     
}
