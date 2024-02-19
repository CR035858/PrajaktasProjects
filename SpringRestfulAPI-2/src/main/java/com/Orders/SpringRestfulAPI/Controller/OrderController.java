package com.Orders.SpringRestfulAPI.Controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.Orders.SpringRestfulAPI.Model.Order;
import com.Orders.SpringRestfulAPI.Service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
	
	@Autowired
	private final OrderService orderService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Order saveOrder(@Valid @RequestBody Order order) {
		order.getLineItems().forEach(lineItem -> lineItem.setOrder(order));      //Important to save the order inside the lineitem
		return this.orderService.save(order);
	}

	@GetMapping
	public Map<String, Object> fetchOrders(
			@RequestParam(name = "page", defaultValue = "1", required = false) int page, 
			@RequestParam(name = "size", defaultValue = "10", required = false) int size, 
			@RequestParam(name = "order", defaultValue = "asc", required = false) String direction, 
			@RequestParam(name = "field", defaultValue = "customerName", required = false)String property
			){
		return this.orderService.fetchOrders(page, size, direction, property);
	}
	
	@GetMapping("/price")
	public Map<String, Object> fetchOrdersByPriceRange(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page, 
			@RequestParam(name = "size", defaultValue = "10", required = false) int size, 
			@RequestParam(name = "min", defaultValue = "500", required = false) double min,
			@RequestParam(name = "max", defaultValue = "2000", required = false) double max
			
			){
		return this.orderService.fetchOrdersByPriceRange(page, size, min, max);
	}
	
	@GetMapping("/{id}")
	public Order fetchOrderById(@PathVariable("id") long orderId) {
		return this.orderService.fetchOrderById(orderId);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void deleteOrderById(@PathVariable long id) {
		this.orderService.deleteOrderById(id);
	}
}

