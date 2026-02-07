package com.bkseducate.securityapp.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.bkseducate.securityapp.domain.model.ToolCatalog;
import com.bkseducate.securityapp.infrastructure.persistence.entity.ToolCatalogEntity;

@Mapper(componentModel = "spring",
    uses = { SupplierMapper.class }
)
public abstract class ToolCatalogMapper {
    @Mapping(target = "description", source = "description")
    public abstract ToolCatalogEntity toEntity(ToolCatalog toolCatalog);
    @Autowired
    protected SupplierMapper supplierMapper;

    public ToolCatalog toDomain(ToolCatalogEntity entity) {
        if (entity == null) return null;

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
