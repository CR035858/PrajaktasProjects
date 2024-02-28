package com.gl.aopproj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.gl.aopproj.*")
public class AopprojApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopprojApplication.class, args);
		System.out.println("Welcome to Aspect oriented Programming...");
	}

}
