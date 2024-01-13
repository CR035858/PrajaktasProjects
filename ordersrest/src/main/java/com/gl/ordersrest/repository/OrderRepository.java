package com.gl.ordersrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ordersrest.model.Order;



public interface OrderRepository extends JpaRepository<Order, Long>{

}
