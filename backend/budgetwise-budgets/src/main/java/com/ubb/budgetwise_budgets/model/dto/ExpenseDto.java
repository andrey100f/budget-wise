package com.ubb.budgetwise_budgets.model.dto;

public record ExpenseDto(
    String id,
    String name,
    Float amount,
    String createdAt,
    String userId,
    String budgetId
) {
}
