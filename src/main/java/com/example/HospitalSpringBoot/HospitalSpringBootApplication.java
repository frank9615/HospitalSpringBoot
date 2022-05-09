package com.example.HospitalSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.HospitalSpringBoot.controllers"})
public class HospitalSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalSpringBootApplication.class, args);
	}

}
