package com.ubb.budgetwise_expenses.model.dto;

public record ExpenseDto (
    String id,
    String name,
    Float amount,
    String createdAt,
    String userId,
    String budgetId
) {
}
