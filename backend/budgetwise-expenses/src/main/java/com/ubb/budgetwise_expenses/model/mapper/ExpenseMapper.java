package com.ubb.budgetwise_expenses.model.mapper;

import com.ubb.budgetwise_expenses.model.Expense;
import com.ubb.budgetwise_expenses.model.dto.AddExpenseDto;
import com.ubb.budgetwise_expenses.model.dto.ExpenseDto;
import org.mapstruct.Mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    ExpenseDto mapToDto(Expense expense);

    default Expense mapFromAddDtoToModel(AddExpenseDto addExpenseDto) {
        return Expense.builder()
            .name(addExpenseDto.name())
            .amount(addExpenseDto.amount())
            .createdAt(LocalDate.parse(LocalDate.now().toString(), formatter))
            .budgetId(addExpenseDto.budgetId())
            .userId(addExpenseDto.userId())
            .build();
    }

}
