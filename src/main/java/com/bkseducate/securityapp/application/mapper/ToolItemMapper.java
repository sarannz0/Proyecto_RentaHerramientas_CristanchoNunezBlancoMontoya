package com.bkseducate.securityapp.application.mapper;

import org.mapstruct.Mapper;

import com.bkseducate.securityapp.domain.model.ToolItem;
import com.bkseducate.securityapp.infrastructure.persistence.entity.ToolItemEntity;

@Mapper(componentModel = "spring")
public abstract class ToolItemMapper {
    protected ToolCatalogMapper toolCatalogMapper;
    
    public ToolItem toDomain(ToolItemEntity entity) {
        return ToolItem.recreate(
            entity.getId(),
            toolCatalogMapper.toDomain(entity.getToolCatalogEntity()),
            entity.getStatus(),
            entity.getAvaiable()
        );
    }

    public ToolItemEntity toEntity(ToolItem tool) {
        ToolItemEntity entity = new ToolItemEntity();
        entity.setId(tool.getId());
        entity.setToolCatalogEntity(toolCatalogMapper.toEntity(tool.getToolCatalog()));
        entity.setStatus(tool.getStatus());
        return entity;
    }
}
