package com.bkseducate.securityapp.application.mapper;

import org.mapstruct.Mapper;

import com.bkseducate.securityapp.domain.model.ToolCatalog;
import com.bkseducate.securityapp.infrastructure.persistence.entity.ToolCatalogEntity;

@Mapper(componentModel = "spring")
public abstract class ToolCatalogMapper {
    public abstract ToolCatalogEntity toEntity(ToolCatalog toolCatalog);
    protected SupplierMapper supplierMapper;

    public ToolCatalog toDomain(ToolCatalogEntity entity) {
        return ToolCatalog.reconstruct(
            entity.getId(),
            supplierMapper.toDomain(entity.getSupplier()),
            entity.getName(),
            entity.getPrice(),
            entity.getStatus(),
            entity.getDescription(),
            entity.getImageUrl()
        );
    }
}
