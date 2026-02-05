package com.bkseducate.securityapp.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bkseducate.securityapp.application.dto.Profile.SupplierResponse;
import com.bkseducate.securityapp.application.dto.Profile.updateProfile.SupplierUpdateResponse;
import com.bkseducate.securityapp.application.dto.Supplier.SupplierRequest;
import com.bkseducate.securityapp.domain.model.SupplierM;
import com.bkseducate.securityapp.domain.model.User;
import com.bkseducate.securityapp.infrastructure.persistence.entity.SupplierEntity;

@Mapper(componentModel = "spring")
public abstract class SupplierMapper {
    @Mapping(source = "supplier.addressId", target = "addressId")
    public abstract SupplierResponse toResponse(User user, SupplierM supplier);

    public abstract SupplierUpdateResponse toUpdateResponse(User user, SupplierM supplier);

    public abstract SupplierRequest toRequest(User user, SupplierM supplier);

    public SupplierM toDomain(SupplierEntity entity) {
        return SupplierM.create(
            entity.getUserId(),  
            entity.getCompanyName(), 
            entity.getAddresId());
    }

    public SupplierEntity toEntity(SupplierM domain) {
        return new SupplierEntity(
            domain.getUserId(),
            domain.getCompanyName(),
            domain.getAddressId()
        );
    }
}
