package com.ubb.budgetwise_budgets.service;

import com.ubb.budgetwise_budgets.client.ExpenseClient;
import com.ubb.budgetwise_budgets.model.dto.AddBudgetDto;
import com.ubb.budgetwise_budgets.model.dto.BudgetDto;
import com.ubb.budgetwise_budgets.model.dto.ExpenseDto;
import com.ubb.budgetwise_budgets.model.mapper.BudgetMapper;
import com.ubb.budgetwise_budgets.repository.BudgetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final BudgetMapper budgetMapper;
    private final ExpenseClient expenseClient;

    public List<BudgetDto> getAllBudgets() {
        return this.budgetRepository.findAll().stream()
            .map(this.budgetMapper::mapToDto)
            .map(budgetDto -> {
                List<ExpenseDto> expenses = this.expenseClient.findExpensesByBudget(budgetDto.id());
                return BudgetDto.builder()
                    .id(budgetDto.id())
                    .name(budgetDto.name())
                    .amount(budgetDto.amount())
                    .createdAt(budgetDto.createdAt())
                    .expenses(expenses)
                    .userId(budgetDto.userId())
                    .build();
            })
            .toList();
    }

    public List<BudgetDto> getBudgetsByUserId(String userId) {
        return this.budgetRepository.findAllByUserId(userId).stream()
            .map(this.budgetMapper::mapToDto)
            .map(budgetDto -> {
                List<ExpenseDto> expenses = this.expenseClient.findExpensesByBudget(budgetDto.id());
                return BudgetDto.builder()
                    .id(budgetDto.id())
                    .name(budgetDto.name())
                    .amount(budgetDto.amount())
                    .createdAt(budgetDto.createdAt())
                    .expenses(expenses)
                    .userId(budgetDto.userId())
                    .build();
            })
            .toList();
    }

    public BudgetDto getBudgetById(String id) {
        return this.budgetRepository.findById(id)
            .map(this.budgetMapper::mapToDto)
            .map(budgetDto -> {
                List<ExpenseDto> expenses = this.expenseClient.findExpensesByBudget(budgetDto.id());
                return BudgetDto.builder()
                    .id(budgetDto.id())
                    .name(budgetDto.name())
                    .amount(budgetDto.amount())
                    .createdAt(budgetDto.createdAt())
                    .expenses(expenses)
                    .build();
            })
            .orElseThrow(() -> new NoSuchElementException("Budget with id " + id + " not found"));
    }

    public BudgetDto addBudget(AddBudgetDto budget) {
        return Optional.of(budget)
            .map(this.budgetMapper::mapFromAddDtoToModel)
            .map(this.budgetRepository::save)
            .map(this.budgetMapper::mapToDto)
            .orElseThrow();
    }

    public void deleteBudget(String id) {
        this.budgetRepository.deleteById(id);
    }

}
