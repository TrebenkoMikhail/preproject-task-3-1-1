package com.habsida.preproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.habsida.preproject.repository")
@EntityScan("com.habsida.preproject.model")
public class Task311Application {

	public static void main(String[] args) {
		SpringApplication.run(Task311Application.class, args);
	}

}
