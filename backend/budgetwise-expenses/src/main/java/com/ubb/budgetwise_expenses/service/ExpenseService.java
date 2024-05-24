package com.ubb.budgetwise_expenses.service;

import com.ubb.budgetwise_expenses.client.BudgetClient;
import com.ubb.budgetwise_expenses.model.Expense;
import com.ubb.budgetwise_expenses.model.dto.AddExpenseDto;
import com.ubb.budgetwise_expenses.model.dto.BudgetDto;
import com.ubb.budgetwise_expenses.model.dto.ExpenseDto;
import com.ubb.budgetwise_expenses.model.exception.InvalidResourceException;
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
    private final BudgetClient budgetClient;

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

    public List<ExpenseDto> getExpensesByUser(String userId) {
        return this.expenseRepository.findAllByUserId(userId).stream()
            .map(this.expenseMapper::mapToDto)
            .toList();
    }

    public ExpenseDto addExpense(AddExpenseDto expense) {
        this.validateExpense(expense);

        return Optional.of(expense)
            .map(this.expenseMapper::mapFromAddDtoToModel)
            .map(this.expenseRepository::save)
            .map(this.expenseMapper::mapToDto)
            .orElseThrow();
    }

    public void deleteExpense(String id) {
        this.expenseRepository.deleteById(id);
    }

    private void validateExpense(AddExpenseDto expense) {
        Float totalExpenses = this.expenseRepository.findAllByBudgetId(expense.budgetId()).stream()
            .map(Expense::getAmount)
            .reduce(0f, Float::sum);
        BudgetDto budget = this.budgetClient.findBudgetById(expense.budgetId());

        if (totalExpenses + expense.amount() > budget.amount()) {
            throw new InvalidResourceException("Total expenses exceed budget amount");
        }
    }

}
