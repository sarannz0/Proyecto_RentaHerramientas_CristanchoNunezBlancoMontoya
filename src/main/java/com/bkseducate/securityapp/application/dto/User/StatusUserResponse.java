package com.bkseducate.securityapp.application.dto.User;

import java.util.UUID;

import com.bkseducate.securityapp.domain.model.UserStatus;

public record StatusUserResponse (
    UUID userId,
    UserStatus status
) {}
