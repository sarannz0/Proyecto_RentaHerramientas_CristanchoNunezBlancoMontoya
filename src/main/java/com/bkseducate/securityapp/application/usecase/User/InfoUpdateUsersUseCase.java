package com.bkseducate.securityapp.application.usecase.User;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bkseducate.securityapp.application.dto.Profile.updateProfile.ProfileUpdate;
import com.bkseducate.securityapp.application.dto.Profile.updateProfile.SupplierUpdateRequest;
import com.bkseducate.securityapp.application.dto.Profile.updateProfile.UpdateRequest;
import com.bkseducate.securityapp.application.dto.Profile.updateProfile.UserUpdateRequest;
import com.bkseducate.securityapp.application.dto.Profile.updateProfile.UserUpdateResponse;
import com.bkseducate.securityapp.application.mapper.SupplierMapper;
import com.bkseducate.securityapp.application.mapper.UserMapper;
import com.bkseducate.securityapp.domain.exceptions.UserNotFoundException;
import com.bkseducate.securityapp.domain.model.SupplierM;
import com.bkseducate.securityapp.domain.model.User;
import com.bkseducate.securityapp.domain.ports.SupplierRepository;
import com.bkseducate.securityapp.domain.ports.UserRepository;

@Service
public class InfoUpdateUsersUseCase {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final SupplierMapper supplierMapper;
    private final SupplierRepository supplierRepository;

    public InfoUpdateUsersUseCase(
        UserRepository userRepository,
        UserMapper userMapper,
        SupplierRepository supplierRepository,
        SupplierMapper supplierMapper
    ) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.supplierMapper = supplierMapper;
        this.supplierRepository = supplierRepository;
    }   

    public ProfileUpdate execute(UpdateRequest request, UUID id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("Usuario con ID: "+ id +" no encontrado"));
        user.updateInfo(request.name(), request.email());
        userRepository.save(user);

        ProfileUpdate profile = request.profile();

        if (profile instanceof SupplierUpdateRequest s) {
            SupplierM supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No se pudo encontrar el usuario con ID "+id));
            supplier.updateInfo(s.companyName(), s.addressId());
            supplierRepository.update(id, supplierMapper.toRequest(user, supplier));
            return supplierMapper.toUpdateResponse(user, supplier);
        }

        return userMapper.toUpdateResponse(user);
    }
}
