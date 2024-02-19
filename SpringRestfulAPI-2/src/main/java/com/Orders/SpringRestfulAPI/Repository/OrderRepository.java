package com.Orders.SpringRestfulAPI.Repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.Orders.SpringRestfulAPI.Model.Order;
import org.springframework.data.domain.Pageable;


public interface OrderRepository extends JpaRepository<Order, Long>{
	
	Page<OrderDto> findByPriceBetween(double min, double max, Pageable pageRequest);

	@Query("select o from Order o where o.email = ?1")
	Optional<Order> findByEmail(String email);

}