package com.bkseducate.securityapp.infrastructure.config;

import com.bkseducate.securityapp.domain.model.Role;
import com.bkseducate.securityapp.domain.model.User;
import com.bkseducate.securityapp.domain.ports.PasswordService;
import com.bkseducate.securityapp.domain.ports.RoleRepository;
import com.bkseducate.securityapp.domain.ports.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Inicializador de datos para roles
 * Crea los roles iniciales si no existen en la base de datos
 */
@Component
@Order(1)
public class DataInitializer implements CommandLineRunner {
    
    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);
    
    private final RoleRepository roleRepository;
    private final PasswordService passwordService;
    private final UserRepository userRepository;

    @Value("${app.admin.email:admin@bkseducate.com}")
    private String adminEmail;

    @Value("${app.admin.password:admin123}")
    private String adminPassword;
    
    public DataInitializer(RoleRepository roleRepository, PasswordService passwordService, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.passwordService = passwordService;
        this.userRepository = userRepository;
    }
    
    @Override
    public void run(String... args) {
        //initializeRoles();
        //initializeAdmin();
    }
    
    private void initializeRoles() {
        logger.info("Inicializando roles...");
        
        // Crear rol USER
        if (!roleRepository.existsByAuthority("ROLE_USER")) {
            Role userRole = Role.reconstruct(
                UUID.fromString("550e8400-e29b-41d4-a716-446655440001"),
                "USER",
                "ROLE_USER"
            );
            roleRepository.save(userRole);
            logger.info("Rol USER creado");
        }
        
        // Crear rol ADMIN
        if (!roleRepository.existsByAuthority("ROLE_ADMIN")) {
            Role adminRole = Role.reconstruct(
                UUID.fromString("550e8400-e29b-41d4-a716-446655440002"),
                "ADMIN",
                "ROLE_ADMIN"
            );
            roleRepository.save(adminRole);
            logger.info("Rol ADMIN creado");
        }
        
        // Crear rol MODERATOR
        if (!roleRepository.existsByAuthority("ROLE_SUPPLIER")) {
            Role moderatorRole = Role.reconstruct(
                UUID.fromString("550e8400-e29b-41d4-a716-446655440003"),
                "SUPPLIER",
                "ROLE_SUPPLIER"
            );
            roleRepository.save(moderatorRole);
            logger.info("Rol SUPPLIER creado");
        }
        
        logger.info("Inicialización de roles completada");
    }

    private void initializeAdmin() {
        logger.info("Creando usuario admin...");
        if (userRepository.existsByEmail("admin@bkseducate.com")) return;
        String password = passwordService.hash(adminPassword);
        User user = User.create("admin", adminEmail, password);
        Role role = roleRepository.findByName("ADMIN")
            .orElseThrow(() -> new RuntimeException("Rol ADMIN no fue encontrado"));
        user.assignRole(role);
        userRepository.save(user);
        logger.info("Usuario admin creado");
    }
}
