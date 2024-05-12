package com.ubb.budgetwise_expenses.service;

import com.ubb.budgetwise_expenses.model.dto.AddExpenseDto;
import com.ubb.budgetwise_expenses.model.dto.ExpenseDto;
import com.ubb.budgetwise_expenses.model.mapper.ExpenseMapper;
import com.ubb.budgetwise_expenses.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;

    public List<ExpenseDto> getAllExpenses() {
        return this.expenseRepository.findAll().stream()
            .map(this.expenseMapper::mapToDto)
            .toList();
    }

    public List<ExpenseDto> getExpensesByBudget(String budgetId) {
        return this.expenseRepository.findAllByBudgetId(budgetId).stream()
            .map(this.expenseMapper::mapToDto)
            .toList();
    }

    public ExpenseDto addExpense(AddExpenseDto expense) {
        return Optional.of(expense)
            .map(this.expenseMapper::mapFromAddDtoToModel)
            .map(this.expenseRepository::save)
            .map(this.expenseMapper::mapToDto)
            .orElseThrow();
    }

    public void deleteExpense(String id) {
        this.expenseRepository.deleteById(id);
    }

}
