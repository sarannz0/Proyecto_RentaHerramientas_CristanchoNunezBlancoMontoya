package com.bkseducate.securityapp.application.usecase.User;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bkseducate.securityapp.application.dto.UserUpdateRequest;
import com.bkseducate.securityapp.application.dto.UserUpdateResponse;
import com.bkseducate.securityapp.application.mapper.UserMapper;
import com.bkseducate.securityapp.domain.exceptions.UserNotFoundException;
import com.bkseducate.securityapp.domain.model.User;
import com.bkseducate.securityapp.domain.ports.UserRepository;

@Service
public class InfoUpdateUsersUseCase {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public InfoUpdateUsersUseCase(
        UserRepository userRepository,
        UserMapper userMapper
    ) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }   

    public UserUpdateResponse execute(UserUpdateRequest request, UUID id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("Usuario con ID: "+ id +" no encontrado"));
        
        user.updateInfo(request.name(), request.email());
        userRepository.save(user); 

        return userMapper.toUpdateResponse(user);
    }
}
