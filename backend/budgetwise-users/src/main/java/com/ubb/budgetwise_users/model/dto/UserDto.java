package com.ubb.budgetwise_users.model.dto;

import lombok.Builder;

@Builder
public record UserDto(
    String id,
    String username,
    String password
) { }
