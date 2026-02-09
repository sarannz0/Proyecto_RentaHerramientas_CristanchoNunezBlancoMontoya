package com.bkseducate.securityapp.infrastructure.persistence.mapper;

import com.bkseducate.securityapp.application.dto.Profile.UserResponse;
import com.bkseducate.securityapp.application.dto.Profile.updateProfile.UserUpdateResponse;
import com.bkseducate.securityapp.application.dto.Role.RoleResponse;
import com.bkseducate.securityapp.domain.model.Role;
import com.bkseducate.securityapp.domain.model.User;
import com.bkseducate.securityapp.infrastructure.persistence.entity.UserEntity;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Mapper MapStruct para conversión entre User (dominio) y UserResponse (DTO)
 * 
 * Con componentModel = "spring", se inyecta como bean de Spring.
 * No usar INSTANCE estático con este modelo.
 */
@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @Autowired
    protected RoleMapper roleMapper;

    public User toDomain(UserEntity userEntity) {
        return User.reconstruct(userEntity.getId(),
            userEntity.getName(),
            userEntity.getEmail(),
            userEntity.getPassword(),
            userEntity.getRoles().stream().map(roleMapper::toDomain).collect(Collectors.toSet()),
            userEntity.getStatus()
        );
    }

    public abstract UserEntity toEntity(User user);
    /**
     * Convierte un User del dominio a UserResponse DTO
     */
    @Mapping(target = "roles", source = "roles")
    public abstract UserResponse toResponse(User user);
    
    /**
     * Convierte una lista de Roles del dominio a lista de RoleResponse DTOs
     */
    public abstract List<RoleResponse> rolesToRoleResponses(Set<Role> roles);
    
    /**
     * Convierte un Role del dominio a RoleResponse DTO
     */
    public abstract RoleResponse toRoleResponse(Role role);

    public abstract UserUpdateResponse toUpdateResponse(User user);
}
