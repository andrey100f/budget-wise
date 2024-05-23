package com.ubb.budgetwise_budgets.controller;

import com.ubb.budgetwise_budgets.model.dto.AddBudgetDto;
import com.ubb.budgetwise_budgets.model.dto.BudgetDto;
import com.ubb.budgetwise_budgets.service.BudgetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/budgets")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    @GetMapping
    public ResponseEntity<List<BudgetDto>> getAllBudgets() {
        return ResponseEntity.ok(this.budgetService.getAllBudgets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BudgetDto> getBudgetById(@PathVariable String id) {
        return ResponseEntity.ok(this.budgetService.getBudgetById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BudgetDto>> getBudgetsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(this.budgetService.getBudgetsByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<BudgetDto> addBudget(@Valid @RequestBody AddBudgetDto budget) throws URISyntaxException {
        BudgetDto addedBudget = this.budgetService.addBudget(budget);
        return ResponseEntity.created(new URI("/api/budgets/" + addedBudget.id())).body(addedBudget);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable String id) {
        this.budgetService.deleteBudget(id);
        return ResponseEntity.noContent().build();
    }

}
