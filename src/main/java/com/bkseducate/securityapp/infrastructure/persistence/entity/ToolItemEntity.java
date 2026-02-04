package com.bkseducate.securityapp.infrastructure.persistence.entity;

import java.util.UUID;

import com.bkseducate.securityapp.domain.model.ToolItemStatus;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class ToolItemEntity {
    @Id
    @Column(columnDefinition = "CHAR(36)", length = 36)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tool_catalog_id", nullable = false, )
    private ToolCatalogEntity toolCatalogEntity;

    @Column(nullable = false)
    private ToolItemStatus status;

    public ToolItemEntity(ToolCatalogEntity toolCatalogEntity, ToolItemStatus status) {
        this.toolCatalogEntity = toolCatalogEntity;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ToolCatalogEntity getToolCatalogEntity() {
        return toolCatalogEntity;
    }

    public void setToolCatalogEntity(ToolCatalogEntity toolCatalogEntity) {
        this.toolCatalogEntity = toolCatalogEntity;
    }

    public ToolItemStatus getStatus() {
        return status;
    }

    public void setStatus(ToolItemStatus status) {
        this.status = status;
    }
    
    
}