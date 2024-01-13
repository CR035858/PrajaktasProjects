package com.gl.ordersrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= {"com.gl.ordersrest.*"})
public class OrdersrestApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdersrestApplication.class, args);
		System.out.println("Welcome to Live Session");
	}

}
