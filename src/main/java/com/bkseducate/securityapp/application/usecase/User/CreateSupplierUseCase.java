package com.bkseducate.securityapp.application.usecase.User;

import org.springframework.stereotype.Service;

import com.bkseducate.securityapp.application.dto.RegisterRequest;
import com.bkseducate.securityapp.application.dto.UserResponse;
import com.bkseducate.securityapp.application.mapper.UserMapper;
import com.bkseducate.securityapp.domain.exceptions.DomainException;
import com.bkseducate.securityapp.domain.model.Role;
import com.bkseducate.securityapp.domain.model.User;
import com.bkseducate.securityapp.domain.ports.PasswordService;
import com.bkseducate.securityapp.domain.ports.RoleRepository;
import com.bkseducate.securityapp.domain.ports.UserRepository;

@Service
public class CreateSupplierUseCase {
    
    private final UserRepository userRepository;
    private final PasswordService passwordService;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public CreateSupplierUseCase(
        UserRepository userRepository,
        PasswordService passwordService,
        RoleRepository roleRepository,
        UserMapper userMapper
    ) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    public UserResponse execute(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email()))
            throw new DomainException("El email "+request.email()+ "ya esta registrado");

        String hashedPassword = passwordService.hash(request.password());
        User user = User.create(request.name(), request.email(), hashedPassword);
        Role role = roleRepository.findByName("SUPPLIER")
            .orElseThrow(() -> new RuntimeException("No se encontro el ROL SUPPLIER"));
        user.assignRole(role);
        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }
}
