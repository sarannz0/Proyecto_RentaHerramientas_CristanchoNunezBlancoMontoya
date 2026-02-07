package com.bkseducate.securityapp.application.usecase.User;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bkseducate.securityapp.application.dto.Profile.UserResponse;
import com.bkseducate.securityapp.domain.exceptions.InfoNotFoundException;
import com.bkseducate.securityapp.domain.model.User;
import com.bkseducate.securityapp.domain.ports.UserRepository;
import com.bkseducate.securityapp.infrastructure.persistence.mapper.UserMapper;

@Service
public class GetUsersForAdminUseCase {

    private final UserMapper userMapper;
    private final UserRepository jpaRepository;

    public GetUsersForAdminUseCase(
        UserMapper userMapper, 
        UserRepository jpaRepository
    ) {        
        this.userMapper = userMapper;
        this.jpaRepository = jpaRepository;
    }

    public List<UserResponse> execute() {
        List<User> list = jpaRepository.findAllUsersBySpecificRoles();
        if (list.isEmpty()) throw new InfoNotFoundException("No hay usuarios de tipo USER o SUPPLIER");
        return list.stream().map(userMapper::toResponse).toList();
    }
}
