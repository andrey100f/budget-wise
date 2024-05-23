package com.ubb.budgetwise_budgets.repository;

import com.ubb.budgetwise_budgets.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, String> {

    List<Budget> findAllByUserId(String userId);

}
