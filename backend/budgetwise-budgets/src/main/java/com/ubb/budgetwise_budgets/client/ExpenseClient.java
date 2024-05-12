package com.ubb.budgetwise_budgets.client;

import com.ubb.budgetwise_budgets.model.dto.ExpenseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "expense-service", url = "${application.config.expenses-url}")
public interface ExpenseClient {

    @GetMapping("/budget/{budgetId}")
    List<ExpenseDto> findExpensesByBudget(@PathVariable String budgetId);

}
