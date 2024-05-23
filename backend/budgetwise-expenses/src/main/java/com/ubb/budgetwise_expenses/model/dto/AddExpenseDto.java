package com.ubb.budgetwise_expenses.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record AddExpenseDto(

    @NotBlank
    String name,

    @Min(1)
    Float amount,

    @NotBlank
    String budgetId,

    String userId
) { }
