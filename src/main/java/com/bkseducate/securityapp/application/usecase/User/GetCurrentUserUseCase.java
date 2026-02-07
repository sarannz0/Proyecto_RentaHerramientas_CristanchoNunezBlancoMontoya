package com.bkseducate.securityapp.application.usecase.User;

import com.bkseducate.securityapp.application.dto.Profile.ProfileResponse;
import com.bkseducate.securityapp.application.dto.Profile.UserResponse;
import com.bkseducate.securityapp.domain.exceptions.UserNotFoundException;
import com.bkseducate.securityapp.domain.model.SupplierM;
import com.bkseducate.securityapp.domain.model.User;
import com.bkseducate.securityapp.domain.ports.SupplierRepository;
import com.bkseducate.securityapp.domain.ports.UserRepository;
import com.bkseducate.securityapp.infrastructure.persistence.mapper.SupplierMapper;
import com.bkseducate.securityapp.infrastructure.persistence.mapper.UserMapper;

import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Caso de uso: Obtener usuario autenticado
 */
@Service
public class GetCurrentUserUseCase {
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final SupplierMapper supplierMapper;
    private final SupplierRepository supplierRepository;
    
    public GetCurrentUserUseCase(
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
    
    public ProfileResponse execute(UUID userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));

        if (user.hasRoleName("SUPPLIER")) {
            SupplierM supplier = supplierRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No se pudo encontrar el usuario con ID "+userId));
            return supplierMapper.toResponse(user, supplier);
        }
        
        return userMapper.toResponse(user);
    }
}
