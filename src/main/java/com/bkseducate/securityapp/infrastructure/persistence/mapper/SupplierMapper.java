package com.bkseducate.securityapp.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.bkseducate.securityapp.application.dto.Profile.SupplierResponse;
import com.bkseducate.securityapp.application.dto.Profile.updateProfile.SupplierUpdateResponse;
import com.bkseducate.securityapp.application.dto.Supplier.SupplierRequest;
import com.bkseducate.securityapp.domain.model.SupplierM;
import com.bkseducate.securityapp.domain.model.User;
import com.bkseducate.securityapp.infrastructure.persistence.entity.SupplierEntity;

@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public abstract class SupplierMapper {

    @Autowired
    protected AddressMapper addressMapper;

    @Mapping(source = "supplier.address", target = "address")
    public abstract SupplierResponse toResponse(User user, SupplierM supplier);

    public abstract SupplierUpdateResponse toUpdateResponse(User user, SupplierM supplier);

    public abstract SupplierRequest toRequest(User user, SupplierM supplier);
    
    public SupplierM toDomain(SupplierEntity entity) {
        return SupplierM.reconstruct(
            entity.getUserId(),
            entity.getCompanyName(),
            addressMapper.toDomain(entity.getAddress()) 
        );
    }

    public SupplierEntity toEntity(SupplierM domain) {
        SupplierEntity entity = new SupplierEntity();
        entity.setUserId(domain.getUserId());
        entity.setCompanyName(domain.getCompanyName());
        entity.setAddres(addressMapper.toEntity(domain.getAddress()));
        return entity;
    }
}
