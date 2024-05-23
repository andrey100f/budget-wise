package com.ubb.budgetwise_expenses.repository;

import com.ubb.budgetwise_expenses.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, String> {
    List<Expense> findAllByBudgetId(String budgetId);
    List<Expense> findAllByUserId(String userId);
}
