package com.gl.ordersrest.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.ordersrest.model.Order;
import com.gl.ordersrest.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
	
	@Autowired
	private final OrderService orderService;
	
	@GetMapping("/greet")
	public String sayHello()
	{
		return "Welcome to Order Mgmt System";
	}
	
	@PostMapping("/save")
	public Order saveOrder(@RequestBody Order order) {
		return this.orderService.save(order);
	}

	@GetMapping("/get")
	public Set<Order> fetchOrders() {
		return this.orderService.fetchOrders();
	}

	@GetMapping("/{id}")
	public Order fetchOrderById(@PathVariable long id) {
	    return this.orderService.fetchOrderById(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteOrderById(@PathVariable long id) {
		this.orderService.deleteOrderById(id);
	}

}
