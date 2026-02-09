package com.bkseducate.securityapp.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

import com.bkseducate.securityapp.domain.exceptions.DomainException;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Catálogo de una herramienta ofrecida por un proveedor")
public class ToolCatalog {
    private UUID id;
    private SupplierM supplier;
    private String name;
    private BigDecimal price;
    private ToolCatalogStatus status;
    private String description;
    private String imageUrl;

    private ToolCatalog() {}

    public static ToolCatalog create(
        SupplierM supplier,
        String name,
        BigDecimal price,
        ToolCatalogStatus status,
        String description,
        String imageUrl
    ) {
        if (supplier == null) throw new DomainException("El proveedor no puede ser nulo");
        if (status == null) throw new DomainException("El estado no puede ser nulo");
        if (price.compareTo(BigDecimal.ZERO) <= 0) throw new DomainException("El precio no puede ser negativo");
        if (name == null || name.isBlank()) throw new DomainException("El nombre no puede estar vacío");
        if (description == null || description.isBlank()) throw new DomainException("La descripción no puede estar vacía");
        if (imageUrl == null || imageUrl.isBlank()) throw new DomainException("La URL de la imagen no puede estar vacía");
        
        ToolCatalog tool = new ToolCatalog();
        tool.id = UUID.randomUUID();
        tool.supplier = supplier;
        tool.name = name;
        tool.price = price;
        tool.status = status;
        tool.description = description;
        tool.imageUrl = imageUrl;
        return tool;
    }

    public static ToolCatalog reconstruct(
        UUID id,
        SupplierM supplier,
        String name,
        BigDecimal price,
        ToolCatalogStatus status,
        String description,
        String imageUrl
    ) { 
        ToolCatalog tool = new ToolCatalog();
        tool.id = id;
        tool.supplier = supplier;
        tool.name = name;
        tool.price = price;
        tool.status = status;
        tool.description = description;
        tool.imageUrl = imageUrl;
        return tool;
    }

    public UUID getId() {
        return id;
    }

    public SupplierM getSupplier() {
        return supplier;
    }

    public String getName() {
        return name;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public ToolCatalogStatus getStatus() {
        return status;
    }
    public String getDescription() {
        return description;
    }
    public String getImageUrl() {
        return imageUrl;
    }
}
