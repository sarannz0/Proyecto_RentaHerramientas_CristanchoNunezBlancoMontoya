package com.bkseducate.securityapp.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bkseducate.securityapp.application.dto.Profile.SupplierResponse;
import com.bkseducate.securityapp.domain.model.SupplierM;
import com.bkseducate.securityapp.domain.model.User;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    @Mapping(source = "supplier.addressId", target = "addressId")
    SupplierResponse toResponse(User user, SupplierM supplier);
}
