package com.example.samplejpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "com.example.samplejpa.repository" })
public class SampleJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleJpaApplication.class, args);
	}
}
