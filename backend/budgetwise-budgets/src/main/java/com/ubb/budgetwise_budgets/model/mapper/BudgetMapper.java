package com.ubb.budgetwise_budgets.model.mapper;

import com.ubb.budgetwise_budgets.model.Budget;
import com.ubb.budgetwise_budgets.model.dto.AddBudgetDto;
import com.ubb.budgetwise_budgets.model.dto.BudgetDto;
import org.mapstruct.Mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface BudgetMapper {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    BudgetDto mapToDto(Budget budget);
    default Budget mapFromAddDtoToModel(AddBudgetDto addBudgetDto) {
        return Budget.builder()
            .name(addBudgetDto.name())
            .amount(addBudgetDto.amount())
            .userId(addBudgetDto.userId())
            .createdAt(LocalDate.parse(LocalDate.now().toString(), formatter))
            .build();
    }

}
