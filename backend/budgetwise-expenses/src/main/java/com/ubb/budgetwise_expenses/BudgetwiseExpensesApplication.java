package com.ubb.budgetwise_expenses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class BudgetwiseExpensesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetwiseExpensesApplication.class, args);
	}

}
