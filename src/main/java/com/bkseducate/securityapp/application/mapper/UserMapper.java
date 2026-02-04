package com.bkseducate.securityapp.application.mapper;

import com.bkseducate.securityapp.application.dto.Profile.UserResponse;
import com.bkseducate.securityapp.application.dto.Role.RoleResponse;
import com.bkseducate.securityapp.application.dto.User.UserUpdateResponse;
import com.bkseducate.securityapp.domain.model.Role;
import com.bkseducate.securityapp.domain.model.User;

import java.util.List;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


/**
 * Mapper MapStruct para conversión entre User (dominio) y UserResponse (DTO)
 * 
 * Con componentModel = "spring", se inyecta como bean de Spring.
 * No usar INSTANCE estático con este modelo.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    
    /**
     * Convierte un User del dominio a UserResponse DTO
     */
    @Mapping(target = "roles", source = "roles")
    UserResponse toResponse(User user);
    
    /**
     * Convierte una lista de Roles del dominio a lista de RoleResponse DTOs
     */
    List<RoleResponse> rolesToRoleResponses(Set<Role> roles);
    
    /**
     * Convierte un Role del dominio a RoleResponse DTO
     */
    RoleResponse toRoleResponse(Role role);


    UserUpdateResponse toUpdateResponse(User user);
}
