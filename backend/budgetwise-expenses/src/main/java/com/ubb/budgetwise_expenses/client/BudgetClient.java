package com.ubb.budgetwise_expenses.client;

import com.ubb.budgetwise_expenses.model.dto.BudgetDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="budget-service", url = "${application.config.budgets-url}")
public interface BudgetClient {

    @GetMapping("/{budgetId}")
    BudgetDto findBudgetById(@PathVariable String budgetId);

}
