package com.bkseducate.securityapp.infrastructure.persistence.adapters.out.persistence;

import com.bkseducate.securityapp.application.dto.UserResponse;
import com.bkseducate.securityapp.application.mapper.UserMapper;
import com.bkseducate.securityapp.domain.model.Role;
import com.bkseducate.securityapp.domain.model.User;
import com.bkseducate.securityapp.domain.model.UserStatus;
import com.bkseducate.securityapp.domain.ports.UserRepository;
import com.bkseducate.securityapp.infrastructure.persistence.entity.RoleEntity;
import com.bkseducate.securityapp.infrastructure.persistence.entity.UserEntity;
import com.bkseducate.securityapp.infrastructure.persistence.repository.UserJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Adaptador que conecta el puerto UserRepository con la implementación JPA
 * Convierte entre entidades de dominio y entidades JPA
 */
@Component
public class UserRepositoryAdapter implements UserRepository {
    
    private final UserJpaRepository jpaRepository;
    private final UserMapper userMapper;
    
    public UserRepositoryAdapter(
        UserJpaRepository jpaRepository,
        UserMapper userMapper
    ) {
        this.userMapper = userMapper;
        this.jpaRepository = jpaRepository;
    }
    
    @Override
    public User save(User user) {
        UserEntity entity = toEntity(user);
        UserEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }
    
    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email)
            .map(this::toDomain);
    }
    
    @Override
    public Optional<User> findById(UUID id) {
        return jpaRepository.findById(id)
            .map(this::toDomain);
    }
    
    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }
    
    @Override
    public void delete(User user) {
        UserEntity entity = toEntity(user);
        jpaRepository.delete(entity);
    }
    
    private UserEntity toEntity(User user) {
        Set<RoleEntity> roleEntities = user.getRoles().stream()
            .map(this::roleToEntity)
            .collect(Collectors.toSet());
        
        return new UserEntity(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getPassword(),
            roleEntities,
            user.getStatus()
        );
    }

    @Override
    public List<User> findAllUsersBySpecificRoles() {
        return jpaRepository.findAllUsersBySpecificRoles().stream().map(this::toDomain).toList();
    }

    
    private User toDomain(UserEntity entity) {
        Set<Role> roles = entity.getRoles().stream()
            .map(this::roleToDomain)
            .collect(Collectors.toSet());
        
        return User.reconstruct(
            entity.getId(),
            entity.getName(),
            entity.getEmail(),
            entity.getPassword(),
            roles,
            entity.getStatus()
        );
    }
    
    private RoleEntity roleToEntity(Role role) {
        return new RoleEntity(
            role.getId(),
            role.getName(),
            role.getAuthority()
        );
    }
    
    private Role roleToDomain(RoleEntity entity) {
        return Role.reconstruct(
            entity.getId(),
            entity.getName(),
            entity.getAuthority()
        );
    }

    
}
