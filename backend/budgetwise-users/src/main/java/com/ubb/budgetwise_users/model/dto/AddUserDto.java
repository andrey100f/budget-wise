package com.ubb.budgetwise_users.model.dto;

import lombok.Builder;

@Builder
public record AddUserDto (
    String username,
    String password
) { }
