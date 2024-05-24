package com.ubb.budgetwise_users.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record AddUserDto (

    @NotBlank
    String username,

    @NotBlank
    String password
) { }
