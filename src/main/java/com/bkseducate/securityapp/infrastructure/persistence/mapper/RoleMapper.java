package com.bkseducate.securityapp.infrastructure.persistence.mapper;

import com.bkseducate.securityapp.domain.model.Role;
import com.bkseducate.securityapp.infrastructure.persistence.entity.RoleEntity;

import org.springframework.stereotype.Component;

/**
 * Utilidad para conversión de Role
 * Clase utilitaria para conversiones de Role a String
 */
@Component
public class RoleMapper {
    
    /**
     * Convierte un Role a String (nombre)
     */
    public String roleToString(Role role) {
        return role != null ? role.getName() : null;
    }
    
    /**
     * Convierte un Role a String (authority)
     */
    public String roleToAuthority(Role role) {
        return role != null ? role.getAuthority() : null;
    }

    public Role toDomain(RoleEntity entity) {
        return Role.reconstruct(
            entity.getId(),
            entity.getName(),
            entity.getAuthority()
        );
    }
}
