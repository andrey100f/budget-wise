package com.ubb.budgetwise_budgets.model.dto;

import lombok.Builder;

@Builder
public record BudgetDto(
    String id,
    String name,
    Float amount,
    String createdAt
) { }
