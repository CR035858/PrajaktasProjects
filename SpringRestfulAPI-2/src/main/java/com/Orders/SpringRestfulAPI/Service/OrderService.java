package com.Orders.SpringRestfulAPI.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.stereotype.Service;
import com.Orders.SpringRestfulAPI.Model.Order;
import com.Orders.SpringRestfulAPI.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import static org.springframework.data.domain.PageRequest.of;

@Service
@RequiredArgsConstructor
public class OrderService {

	@Autowired
	private final OrderRepository repository;                  //Add generic type parameters

	public Order save(Order order) {
		return this.repository.save(order);
	}

	public Map<String, Object> fetchOrders(int page, int size, String sort, String property){
		Sort.Direction direction = sort.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
		PageRequest pageRequest = of(page, size, direction, property);
		Page<Order> pageResponse = this.repository.findAll(pageRequest);
		
		int totalPages = pageResponse.getTotalPages();
		int noOfRecords = pageResponse.getNumberOfElements();
		List<Order> content = pageResponse.getContent();
		
		Map<String, Object> responseMap = new LinkedHashMap<>();
		responseMap.put("pages", totalPages);
		responseMap.put("records", noOfRecords);
		responseMap.put("data", content);
		return responseMap;
	}
	
	public Map<String, Object> fetchOrdersByPriceRange(int page, int size,double min, double max){
		PageRequest pageRequest = of(page, size);
		Page<OrderDto> pageResponse = this.repository.findByPriceBetween(min, max, pageRequest);
		
		int totalPages = pageResponse.getTotalPages();
		int noOfRecords = pageResponse.getNumberOfElements();
		List<OrderDto> content = pageResponse.getContent();
		
		Map<String, Object> responseMap = new LinkedHashMap<>();
		responseMap.put("pages", totalPages);
		responseMap.put("records", noOfRecords);
		responseMap.put("data", content);
		return responseMap;
	}
	
	public Order fetchOrderById(long id) {
		return this.repository.findById(id)
							  .orElseThrow(() -> new IllegalArgumentException("invalid order id passed"));
	}
	
	public void deleteOrderById(long id) {
		this.repository.deleteById(id);
	}
	
	public Order updateOrderById(long id, Order order) {
		return null;
	}
}

