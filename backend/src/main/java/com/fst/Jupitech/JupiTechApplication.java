package com.fst.Jupitech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import jakarta.annotation.PostConstruct;

@SpringBootApplication

public class JupiTechApplication {

	@PostConstruct
	public void init() {
		System.out.println("=== Application démarrée ===");
		System.out.println("=== Controllers chargés ===");
	}

	public static void main(String[] args) {
		SpringApplication.run(JupiTechApplication.class, args);
	}

}
