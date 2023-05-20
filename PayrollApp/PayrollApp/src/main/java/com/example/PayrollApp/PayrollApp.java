package com.example.PayrollApp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PayrollApp {

	public static void main(String[] args) {
		SpringApplication.run(PayrollApp.class, args);
	}
	
	@Bean
	public ModelMapper getmodelMapper() {
		
		return new ModelMapper();
	}

}
