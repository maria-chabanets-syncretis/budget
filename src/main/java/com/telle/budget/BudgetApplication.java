package com.telle.budget;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;

@SpringBootApplication
public class BudgetApplication {

	public static void main(String[] args) {
		Locale.setDefault(Locale.UK);
		SpringApplication.run(BudgetApplication.class, args);
	}

}
