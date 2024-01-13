package com.gl.ordersrest.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ordersrest.model.Order;
import com.gl.ordersrest.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	@Autowired
	private final OrderRepository repository;                  //Add generic type parameters

	public Order save(Order order) {
		return this.repository.save(order);
	}

	public Set<Order> fetchOrders() {
        return Set.copyOf(this.repository.findAll());
    }

	public Order fetchOrderById(long id) {
		return this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid order id passed"));
	}

	public void deleteOrderById(long id) {
		this.repository.deleteById(id);
	}

	/*
	 * public Order updateOrderById(long id, Order order) { return
	 * this.repository.save(order); }
	 */
}
