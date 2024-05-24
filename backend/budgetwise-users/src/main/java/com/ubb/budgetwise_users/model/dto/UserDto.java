package com.ubb.budgetwise_users.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UserDto(
    String id,

    @NotBlank
    String username,

    @NotBlank
    String password
) { }
