package com.bkseducate.securityapp.domain.model;

import java.util.UUID;

import com.bkseducate.securityapp.domain.exceptions.DomainException;


public class ToolItem {
    private UUID id;
    private ToolCatalog toolCatalog;
    private ToolItemStatus status;

    private ToolItem() {}

    public ToolItem create(
        ToolCatalog toolCatalog,
        ToolItemStatus status
    ) {
        ToolItem toolItem = new ToolItem();
        toolItem.id = UUID.randomUUID();
        toolItem.toolCatalog = toolCatalog;
        toolItem.status = status;
        return toolItem;
    }

    public ToolItem recreate(
        UUID id,
        ToolCatalog toolCatalog,
        ToolItemStatus status
    ) {
        if (toolCatalog == null) throw new DomainException("El catalogo debe contener al menos un objeto");
        if (status == null) throw new DomainException("El catalogo debe contener al menos un estado  AVAIBLE,UNAVAIABLE,DAMAGED,MAINTENANCE")

        ToolItem toolItem = new ToolItem();
        toolItem.id = id;
        toolItem.toolCatalog = toolCatalog;
        toolItem.status = status;
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
    
}
