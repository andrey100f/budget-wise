package com.ubb.budgetwise_config_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class BudgetwiseConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetwiseConfigServerApplication.class, args);
	}

}
