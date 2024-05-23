package com.ubb.budgetwise_expenses.controller;

import com.ubb.budgetwise_expenses.model.dto.AddExpenseDto;
import com.ubb.budgetwise_expenses.model.dto.ExpenseDto;
import com.ubb.budgetwise_expenses.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping
    public ResponseEntity<List<ExpenseDto>> getAllExpenses() {
        return ResponseEntity.ok(this.expenseService.getAllExpenses());
    }

    @PostMapping
    public ResponseEntity<ExpenseDto> addExpense(@Valid @RequestBody AddExpenseDto expenseDto) throws URISyntaxException {
        ExpenseDto addedExpense = this.expenseService.addExpense(expenseDto);
        return ResponseEntity.created(new URI("/api/expenses/" + addedExpense.id())).body(addedExpense);
    }

    @GetMapping("/budget/{budgetId}")
    public ResponseEntity<List<ExpenseDto>> getExpensesByBudget(@PathVariable String budgetId) {
        return ResponseEntity.ok(this.expenseService.getExpensesByBudget(budgetId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ExpenseDto>> getExpensesByUser(@PathVariable String userId) {
        return ResponseEntity.ok(this.expenseService.getExpensesByUser(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable String id) {
        this.expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }

}
