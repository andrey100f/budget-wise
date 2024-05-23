package com.ubb.budgetwise_budgets.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record AddBudgetDto (
    @NotBlank
    String name,

    @Min(1)
    Float amount,

    String userId
) { }
