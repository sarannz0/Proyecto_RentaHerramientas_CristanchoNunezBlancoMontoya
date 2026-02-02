package com.bkseducate.securityapp.infrastructure.persistence.repository;

import com.bkseducate.securityapp.domain.model.UserStatus;
import com.bkseducate.securityapp.infrastructure.persistence.entity.UserEntity;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repositorio JPA para UserEntity
 */
@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {
    
    Optional<UserEntity> findByEmail(String email);
    
    boolean existsByEmail(String email);

    @Query("SELECT u FROM UserEntity u JOIN u.roles r WHERE r.name IN ('USER', 'SUPPLIER')")
    List<UserEntity> findAllUsersBySpecificRoles();
    
    @Modifying
    @Transactional
    @Query("UPDATE UserEntity u SET u.status = ?2 WHERE u.id = ?1")
    Integer setStatus(UUID userId, UserStatus status);
}
