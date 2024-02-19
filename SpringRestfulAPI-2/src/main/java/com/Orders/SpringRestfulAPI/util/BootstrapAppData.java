package com.Orders.SpringRestfulAPI.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import com.Orders.SpringRestfulAPI.Model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.Orders.SpringRestfulAPI.Model.LineItem;
import com.Orders.SpringRestfulAPI.Model.Order;
import com.Orders.SpringRestfulAPI.Model.Role;
import com.Orders.SpringRestfulAPI.Repository.OrderRepository;
import com.Orders.SpringRestfulAPI.Repository.UserRepository;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Configuration
@RequiredArgsConstructor
public class BootstrapAppData {
	
	private final OrderRepository repository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	private final Faker faker = new Faker();
	@Value("${app.orderCount}")
	private int orderCount;

	@EventListener(ApplicationReadyEvent.class)
	public void handleApplicationreadyEvent(ApplicationReadyEvent event) {
		
		IntStream.range(0, orderCount).forEach(index -> {
			LocalDate orderDate = faker.date().past(2, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			Name customerName = faker.name();
			Order order = Order.builder()
								.customerName(customerName.firstName())
								.orderDate(orderDate)
								.email(customerName.firstName()+"@"+faker.internet().domainName())
								.price(index)
								.build();
			
			IntStream.range(0, faker.number().numberBetween(2, 5)).forEach(val -> {
				LineItem lineItem = LineItem.builder()
										.name(faker.commerce().productName())
										.price(faker.number().randomDouble(2, 800, 1200))
										.qty(faker.number().numberBetween(2, 6))
										.build();
				double totalOrderPrice = order.getPrice() + lineItem.getPrice() * lineItem.getQty();
				order.setPrice(totalOrderPrice);
				order.addLineItem(lineItem);
			});
			this.repository.save(order);
		});
	}
	
	@EventListener(ApplicationReadyEvent.class)
	@Transactional
	public void bootstrapUserData(ApplicationReadyEvent event) {
		String encodedPassword = this.passwordEncoder.encode("welcome");
		User kiran = User.builder()
							.email("kiran@gmail.com")
							.name("kiran")
							.password(encodedPassword)
							.city(faker.address().city())
							.salary(faker.number().randomDouble(2, 3000, 5000))
							.build();
		User vinay = User.builder()
				           .email("vinay@gmail.com")
				           .name("vinay").password(encodedPassword)
							.city(faker.address().city())
							.salary(faker.number().randomDouble(2, 3000, 5000))
				           .build();
		
		Role userRole = Role.builder().roleName("ROLE_USER").build();
		Role adminRole = Role.builder().roleName("ROLE_ADMIN").build();
		
		kiran.addRole(userRole);	
		vinay.addRole(userRole);
		vinay.addRole(adminRole);
		
		this.userRepository.save(kiran);
		this.userRepository.save(vinay);
	}
}



