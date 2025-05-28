package com.entbooking.eticketing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class EticketingApplication {

	public static void main(String[] args) {
		SpringApplication.run(EticketingApplication.class, args);
	}

}
