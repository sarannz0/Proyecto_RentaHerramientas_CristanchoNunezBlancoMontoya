package com.bkseducate.securityapp.domain.model;

import java.util.UUID;

import com.bkseducate.securityapp.domain.exceptions.DomainException;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Ítem físico de una herramienta (unidad disponible para alquiler)")
public class ToolItem {
    private UUID id;
    private ToolCatalog toolCatalog;
    private ToolItemStatus status;
    private Boolean avaiable;

    private ToolItem() {}

    public static ToolItem create(
        ToolCatalog toolCatalog,
        ToolItemStatus status,
        Boolean avaiable
    ) {
        if (toolCatalog == null) throw new DomainException("El catalogo debe contener al menos un objeto");
        if (status == null) throw new DomainException("El catalogo debe contener al menos un estado");
        if (avaiable == null) throw new DomainException("El catalogo requiere un valor BOOLEAN en el campo AVAIBLE");
        ToolItem toolItem = new ToolItem();
        toolItem.id = UUID.randomUUID();
        toolItem.toolCatalog = toolCatalog;
        toolItem.status = status;
        toolItem.avaiable = avaiable;
        return toolItem;
    }

    public static ToolItem recreate(
        UUID id,
        ToolCatalog toolCatalog,
        ToolItemStatus status,
        Boolean avaiable
    ) {
        ToolItem toolItem = new ToolItem();
        toolItem.id = id;
        toolItem.toolCatalog = toolCatalog;
        toolItem.status = status;
        toolItem.avaiable = avaiable;
        return toolItem;
    }

    public UUID getId() {
        return id;
    }

    public ToolCatalog getToolCatalog() {
        return toolCatalog;
    }

    public ToolItemStatus getStatus() {
        return status;
    }

    public Boolean getAvaiable() {
        return avaiable;
    }
    
}
