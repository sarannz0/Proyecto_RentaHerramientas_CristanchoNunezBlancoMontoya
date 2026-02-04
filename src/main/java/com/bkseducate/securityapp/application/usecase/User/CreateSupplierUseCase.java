package com.bkseducate.securityapp.application.usecase.User;

import org.springframework.stereotype.Service;

import com.bkseducate.securityapp.application.dto.Profile.SupplierResponse;
import com.bkseducate.securityapp.application.dto.Supplier.SupplierRequest;
import com.bkseducate.securityapp.application.mapper.SupplierMapper;
import com.bkseducate.securityapp.domain.exceptions.DomainException;
import com.bkseducate.securityapp.domain.model.Role;
import com.bkseducate.securityapp.domain.model.SupplierM;
import com.bkseducate.securityapp.domain.model.User;
import com.bkseducate.securityapp.domain.ports.PasswordService;
import com.bkseducate.securityapp.domain.ports.RoleRepository;
import com.bkseducate.securityapp.domain.ports.SupplierRepository;
import com.bkseducate.securityapp.domain.ports.UserRepository;

@Service
public class CreateSupplierUseCase {
    
    private final UserRepository userRepository;
    private final PasswordService passwordService;
    private final RoleRepository roleRepository;
    private final SupplierMapper supplierMapper;
    private final SupplierRepository supplierRepository;

    public CreateSupplierUseCase(
        UserRepository userRepository,
        PasswordService passwordService,
        RoleRepository roleRepository,
        SupplierMapper supplierMapper,
        SupplierRepository supplierRepository
    ) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
        this.roleRepository = roleRepository;
        this.supplierMapper = supplierMapper;
        this.supplierRepository = supplierRepository;
    }

    public SupplierResponse execute(SupplierRequest request) {
        if (userRepository.existsByEmail(request.email()))
            throw new DomainException("El email "+request.email()+ "ya esta registrado");

        String hashedPassword = passwordService.hash(request.password());
        User user = User.create(request.name(), request.email(), hashedPassword);
        
        Role role = roleRepository.findByName("SUPPLIER")
            .orElseThrow(() -> new RuntimeException("No se encontro el ROL SUPPLIER"));
        user.assignRole(role);
        SupplierM supplier = SupplierM.create(user.getId(), request.companyName(), request.addressId());

        SupplierM savedSupplier = supplierRepository.save(supplier);
        User savedUser = userRepository.save(user);
        return supplierMapper.toResponse(savedUser, savedSupplier);
    }
}
