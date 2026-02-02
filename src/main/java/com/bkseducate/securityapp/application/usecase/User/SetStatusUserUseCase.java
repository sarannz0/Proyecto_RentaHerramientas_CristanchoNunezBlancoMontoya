package com.bkseducate.securityapp.application.usecase.User;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bkseducate.securityapp.application.dto.StatusUserResponse;
import com.bkseducate.securityapp.domain.exceptions.UserNotFoundException;
import com.bkseducate.securityapp.domain.model.User;
import com.bkseducate.securityapp.domain.model.UserStatus;
import com.bkseducate.securityapp.domain.ports.UserRepository;

@Service
public class SetStatusUserUseCase {
    private final UserRepository userRepository;

    public SetStatusUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public StatusUserResponse execute(UUID userId, UserStatus status) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("Usuario con ID "+userId+" no econtrado"));
        
        userRepository.setStatus(user.getId(), status);
        return new StatusUserResponse(user.getId(), status);
    }


}
