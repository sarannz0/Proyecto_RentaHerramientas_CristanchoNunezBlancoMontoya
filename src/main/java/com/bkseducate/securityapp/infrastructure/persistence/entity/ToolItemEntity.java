package com.bkseducate.securityapp.infrastructure.persistence.entity;

import java.util.UUID;

import com.bkseducate.securityapp.domain.model.ToolItemStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tool_items")
public class ToolItemEntity {
    @Id
    @Column(columnDefinition = "CHAR(36)", length = 36)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tool_catalog_id", nullable = false, updatable = false)
    private ToolCatalogEntity toolCatalogEntity;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ToolItemStatus status;

    @Column(nullable = false)
    private Boolean avaiable;

    public ToolItemEntity() {
    }

    public ToolItemEntity(ToolCatalogEntity toolCatalogEntity, ToolItemStatus status, Boolean avaiable) {
        this.toolCatalogEntity = toolCatalogEntity;
        this.status = status;
        this.avaiable = avaiable;
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

    public Boolean getAvaiable() {
        return avaiable;
    }

    public void setAvaiable(Boolean avaiable) {
        this.avaiable = avaiable;
    }
    
    
}