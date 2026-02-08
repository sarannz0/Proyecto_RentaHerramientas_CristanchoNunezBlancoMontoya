package com.bkseducate.securityapp.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.bkseducate.securityapp.domain.model.ToolItem;
import com.bkseducate.securityapp.infrastructure.persistence.entity.ToolItemEntity;

@Mapper(componentModel = "spring")
public abstract class ToolItemMapper {
    @Autowired
    protected ToolCatalogMapper toolCatalogMapper;
    
    public ToolItem toDomain(ToolItemEntity entity) {
        return ToolItem.recreate(
            entity.getId(),
            toolCatalogMapper.toDomain(entity.getToolCatalogEntity()),
            entity.getStatus(),
            entity.getAvailable()
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
