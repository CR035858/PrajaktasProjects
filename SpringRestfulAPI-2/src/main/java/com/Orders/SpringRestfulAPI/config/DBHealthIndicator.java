package com.Orders.SpringRestfulAPI.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Configuration;
import com.Orders.SpringRestfulAPI.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;


@Configuration
@RequiredArgsConstructor
class DBHealthIndicator implements HealthIndicator {
	
	@Autowired
	private final OrderRepository repository;
	
	@Override
	public Health health() {
		long orders = this.repository.count();
		if(orders > 0 ) {
			return Health.up().withDetail("DB-status", "DB is healthy").build();
		}
		return Health.down().withDetail("DB-status", "DB is unhealthy").build();
	}
}


@Configuration
@RequiredArgsConstructor
class APIGatewayHealthIndicator implements HealthIndicator {
	
	@Autowired
	private final OrderRepository repository;
	
	@Override
	public Health health() {
		long orders = this.repository.count();
		if(orders > 0 ) {
			return Health.up().withDetail("API-Gateway", "API-Gateway is healthy").build();
		}
		return Health.down().withDetail("API-Gateway", "API-Gateway is unhealthy").build();
	}
}


